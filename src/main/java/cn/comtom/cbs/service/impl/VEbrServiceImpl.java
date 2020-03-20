package cn.comtom.cbs.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.BcEbrPlatformMapper;
import cn.comtom.cbs.dao.VEbrMapper;
import cn.comtom.cbs.dto.ebm.EbrDto;
import cn.comtom.cbs.dto.ebm.EbrQueryRequest;
import cn.comtom.cbs.dto.ebr.EbrTypeDto;
import cn.comtom.cbs.enums.ResTypeDictEnum;
import cn.comtom.cbs.model.BcEbrPlatform;
import cn.comtom.cbs.model.SysRegionArea;
import cn.comtom.cbs.model.VEbr;
import cn.comtom.cbs.service.BcEbrAdapterService;
import cn.comtom.cbs.service.BcEbrTerminalService;
import cn.comtom.cbs.service.IVEbrService;
import cn.comtom.cbs.service.SysParamsService;
import cn.comtom.cbs.service.SysRegionAreaService;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Condition;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class VEbrServiceImpl extends AbstractService<VEbr> implements IVEbrService  {

    @Autowired
    private VEbrMapper vEbrMapper;
    
    @Autowired
    private SysParamsService sysParamsService;
    
    @Resource
    private BcEbrPlatformMapper bcEbrPlatformMapper;
    
    @Autowired
    private SysRegionAreaService sysRegionAreaService;
    
    @Autowired
    private BcEbrTerminalService bcEbrTerminalService;
    
    @Autowired
    private BcEbrAdapterService bcEbrAdapterService;
    
    @Override
    public List<EbrDto> getPlatformEbrlist(EbrQueryRequest request) {
	    //查询本级平台信息
        String thisEbrId = sysParamsService.getValByKey("ebr_platform_id_");
        BcEbrPlatform platform = bcEbrPlatformMapper.selectByPrimaryKey(thisEbrId);
        if(platform == null){
          return null;
        }
        //获取本级平台的区域编码
        String ebrAreaCode = platform.getAreacode();
    	List<String> areaCodes = Lists.newArrayList();
    	areaCodes.add(ebrAreaCode);
    	request.setAreaCodes(areaCodes);
    	
    	return list(request);
    }

    @Override
    public List<EbrDto> list(EbrQueryRequest request) {
    	Condition condition = new Condition(VEbr.class);
		condition.createCriteria().andIn("areaCode", request.getAreaCodes());
		List<VEbr> vEbrList = vEbrMapper.selectByCondition(condition);
		return Optional.ofNullable(vEbrList).orElse(Collections.emptyList()).stream().filter(Objects::nonNull).map(ebr -> {
              EbrDto ebrInfo = new EbrDto();
              BeanUtils.copyProperties(ebr,ebrInfo);
              return ebrInfo;
        }).distinct().collect(Collectors.toList());
    }
    
	@Override
	public List<EbrTypeDto> countEbrType() {
		EbrQueryRequest request = new EbrQueryRequest();
	      //查询本级平台信息
        String thisEbrId = sysParamsService.getValByKey("ebr_platform_id_");
        BcEbrPlatform platform = bcEbrPlatformMapper.selectByPrimaryKey(thisEbrId);
        if(platform == null){
            return null;
        }
        //获取本级平台的区域编码
        String ebrAreaCode = platform.getAreacode();
        Condition condition = new Condition(VEbr.class);
		condition.createCriteria().andEqualTo("areaCode", ebrAreaCode);
        List<SysRegionArea> regionAreaInfoList = sysRegionAreaService.findByCondition(condition);
        //查询所有下一级区域编码
        List<String> areaCodes = Optional.ofNullable(regionAreaInfoList).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .map(SysRegionArea::getAreacode)
                .distinct()
                .collect(Collectors.toList());
        //本级平台的也加上
        areaCodes.add(ebrAreaCode);
        request.setAreaCodes(areaCodes);
        
        //查询所有播出资源信息列表
        List<EbrDto> ebrViewList = list(request);
        List<EbrDto> ebrInfoViewList = Optional.ofNullable(ebrViewList).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .filter(ebrInfo -> {
                    if("01".equals(ebrInfo.getEbrType())){
                        //平台
                        return !ebrAreaCode.equals(ebrInfo.getAreaCode());
                    }
                    return ebrAreaCode.equals(ebrInfo.getAreaCode()) && !"09".equals(ebrInfo.getEbrType());
                })
                .collect(Collectors.toList());
        //遍历出所有播出资源的类型。
        List<EbrTypeDto> ebrTypeEntityList = Optional.ofNullable(ebrInfoViewList).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .map(ebrInfo -> {
                	EbrTypeDto ebrTypeEntity = new EbrTypeDto();
                    //ebrTypeEntity.setEbrTypeName(ebrInfo.getEbrTypeName());
                    ebrTypeEntity.setEbrType(ebrInfo.getEbrType());
                    ebrTypeEntity.setResType(ebrInfo.getResType());
                    ebrTypeEntity.setEbrTypeName(ResTypeDictEnum.getDescByKey(ebrInfo.getResType()));
                    return ebrTypeEntity;
                })
                .distinct()
                .filter(distinctByKey(EbrTypeDto::getResType))
                .filter(ebrTypeEntity -> ObjectUtils.allNotNull(ebrTypeEntity))
                .collect(Collectors.toList());
        log.info("listForType1:{}",JSON.toJSONString(ebrTypeEntityList));
        //将播出资源列表归类到对应的类型下
        ebrTypeEntityList = Optional.ofNullable(ebrTypeEntityList).orElse(Collections.emptyList()).stream()
                .peek(ebrTypeEntity -> {
                    List<EbrDto> ebrInfoList = Optional.ofNullable(ebrInfoViewList).orElse(Collections.emptyList()).stream()
                            .filter(Objects::nonNull)
                            .filter(ebrInfo -> StringUtils.isNotBlank(ebrInfo.getResType()))
                            .filter(ebrInfo ->
                                    ebrTypeEntity.getResType().equals(ebrInfo.getResType())
                            ).collect(Collectors.toList());
                    //ebrTypeEntity.setEbrDtoList(ebrInfoList);
                    //1:运行2:停止3:故障4:故障恢复
                    Map<String, Long> countMap = ebrInfoList.stream().collect(Collectors.groupingBy(EbrDto::getEbrState, Collectors.counting()));
                    ebrTypeEntity.setTotalCount((long) ebrInfoList.size());
                    ebrTypeEntity.setRunCount(countMap.get("1")==null?0:countMap.get("1"));
                    ebrTypeEntity.setStopCount(countMap.get("2")==null?0:countMap.get("2"));
                    ebrTypeEntity.setFailureCount(countMap.get("3")==null?0:countMap.get("3"));
                    ebrTypeEntity.setFailureRecCount(countMap.get("4")==null?0:countMap.get("4"));
                    //如果是大喇叭管控，展示下级适配器、终端统计数据
                    if("0314".equals(ebrTypeEntity.getResType())) {
                    	//终端
                    	List<EbrTypeDto> ebrTypeChList = Lists.newArrayList();
                    	EbrTypeDto ebrTerminal = new EbrTypeDto();
                    	ebrTerminal.setEbrTypeName("04");
                    	ebrTerminal.setEbrTypeName("终端");
                    	ebrTerminal.setTotalCount(bcEbrTerminalService.countByState(thisEbrId, ""));
                    	ebrTerminal.setRunCount(bcEbrTerminalService.countByState(thisEbrId, "1"));
                    	ebrTerminal.setStopCount(bcEbrTerminalService.countByState(thisEbrId, "2"));
                    	ebrTerminal.setFailureCount(bcEbrTerminalService.countByState(thisEbrId, "3"));
                    	ebrTerminal.setFailureRecCount(bcEbrTerminalService.countByState(thisEbrId, "4"));
                    	ebrTypeChList.add(ebrTerminal);
                    	//适配器
                     	EbrTypeDto ebrAdapter = new EbrTypeDto();
                     	ebrAdapter.setEbrTypeName("02");
                     	ebrAdapter.setEbrTypeName("适配器");
                     	ebrAdapter.setTotalCount(bcEbrAdapterService.countByState(thisEbrId, ""));
                     	ebrAdapter.setRunCount(bcEbrAdapterService.countByState(thisEbrId, "1"));
                     	ebrAdapter.setStopCount(bcEbrAdapterService.countByState(thisEbrId, "2"));
                     	ebrAdapter.setFailureCount(bcEbrAdapterService.countByState(thisEbrId, "3"));
                     	ebrAdapter.setFailureRecCount(bcEbrAdapterService.countByState(thisEbrId, "4"));
                    	ebrTypeChList.add(ebrAdapter);
                    	//ebrTypeEntity.setEbrtypeDtoList(ebrTypeChList);
                    	ebrTypeEntity.setEbrtypeDto(ebrTerminal);
                    }
                }).collect(Collectors.toList());
        log.info("listForType2:{}",JSON.toJSONString(ebrTypeEntityList));
		return ebrTypeEntityList;
	}
	
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
