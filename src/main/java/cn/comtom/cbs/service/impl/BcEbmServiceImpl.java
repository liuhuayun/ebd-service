package cn.comtom.cbs.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.BcDispatchFlowMapper;
import cn.comtom.cbs.dao.BcEbmDispatchMapper;
import cn.comtom.cbs.dao.BcEbmMapper;
import cn.comtom.cbs.dao.BcEbrPlatformMapper;
import cn.comtom.cbs.dao.BcSchemeEbrMapper;
import cn.comtom.cbs.dao.BcSchemeMapper;
import cn.comtom.cbs.dto.ebm.EbmInfoAllDto;
import cn.comtom.cbs.dto.ebm.EbrDto;
import cn.comtom.cbs.model.BcDispatchFlow;
import cn.comtom.cbs.model.BcEbm;
import cn.comtom.cbs.model.BcEbmDispatch;
import cn.comtom.cbs.model.BcEbrPlatform;
import cn.comtom.cbs.model.BcScheme;
import cn.comtom.cbs.model.BcSchemeEbr;
import cn.comtom.cbs.model.SysRegionArea;
import cn.comtom.cbs.service.BcEbmDispatchService;
import cn.comtom.cbs.service.BcEbmService;
import cn.comtom.cbs.service.SysRegionAreaService;
import tk.mybatis.mapper.entity.Condition;


/**
 * BcEbm Service实现类，Created on 2019-08-12.
 * @author liuhy
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbmServiceImpl extends AbstractService<BcEbm> implements BcEbmService {

    @Resource
    private BcEbmMapper bcEbmMapper;

    @Resource
    private BcEbrPlatformMapper bcEbrPlatformMapper;

    @Resource
    private BcSchemeEbrMapper bcSchemeEbrMapper;

    @Resource
    private BcSchemeMapper bcSchemeMapper;

    @Resource
    private BcDispatchFlowMapper bcDispatchFlowMapper;

    @Resource
    private SysRegionAreaService sysRegionAreaService;

    @Resource
    private BcEbmDispatchService bcEbmDispatchService;
    @Resource
    private BcEbmDispatchMapper bcEbmDispatchMapper;
	@Override
	public List<BcEbm> queryListLimit(Integer limit) {
		Condition condition = new Condition(BcEbm.class);
		condition.setOrderByClause("createTime desc");
		condition.createCriteria().andEqualTo("sendflag", 1).andEqualTo("msgtype", 1);
		return bcEbmMapper.selectByCondition(condition);
	}

	@Override
	public EbmInfoAllDto findEbmInfoAll(String ebmId) {
		//查询ebm消息
		BcEbm bcEbm = bcEbmMapper.selectByPrimaryKey(ebmId);
		BcEbrPlatform ebrPlatform = bcEbrPlatformMapper.selectByPrimaryKey(bcEbm.getSendercode());
		EbmInfoAllDto ebmInfoDto = new EbmInfoAllDto();
		Optional.ofNullable(bcEbm).ifPresent(ele->{
			BeanUtils.copyProperties(ele,ebmInfoDto);
		});
		Optional.ofNullable(ebrPlatform).ifPresent(ele->{
			ebmInfoDto.setLatitude(ele.getLatitude());
			ebmInfoDto.setLongitude(ele.getLongitude());
			ebmInfoDto.setEbrId(ebrPlatform.getPsebrid());
		});
		//查询方案
		BcScheme criSche = new BcScheme();
		criSche.setEbmid(ebmId);
		BcScheme bcScheme = bcSchemeMapper.selectOne(criSche);
		Optional.ofNullable(bcScheme).ifPresent(ele->{
			//查询方案关联资源
			Condition condition = new Condition(BcSchemeEbr.class);
			condition.createCriteria().andEqualTo("schemeid", ele.getSchemeid());
			List<BcSchemeEbr> schemeEbrList = bcSchemeEbrMapper.selectByCondition(condition);
	        List<EbrDto> ebrInfoList = Optional.ofNullable(schemeEbrList).orElse(Collections.emptyList()).stream()
	                .filter(Objects::nonNull)
	                .map(schemeEbr -> {
	                	EbrDto ebrInfo = bcEbrPlatformMapper.getEbrInfoById(schemeEbr.getEbrid());
	                    return ebrInfo;
	                }).distinct().filter(Objects::nonNull).collect(Collectors.toList());
	        ebmInfoDto.setEbrDtoList(ebrInfoList);
		});
		//查询调度流程
		BcDispatchFlow dispatchFlow = bcDispatchFlowMapper.selectByPrimaryKey(bcEbm.getFlowid());
		Optional.ofNullable(dispatchFlow).ifPresent(df->{
			ebmInfoDto.setFlowstage(df.getFlowstage());
			ebmInfoDto.setFlowstate(df.getFlowstate());
		});
		//查询分发状态
		BcEbmDispatch bcEbmDispatch = new BcEbmDispatch();
		bcEbmDispatch.setEbmid(ebmId);
		List<BcEbmDispatch> ebmDispatchList = bcEbmDispatchMapper.select(bcEbmDispatch);
		Map<String, Object> ebrStateMap = Optional.ofNullable(ebmDispatchList)
				.orElse(Lists.newArrayList()).stream().collect(Collectors.toMap(item->{
			if(StringUtils.isNotBlank(item.getPsebrid())) {
				return item.getPsebrid();
			}else if(StringUtils.isNotBlank(item.getBsebrid())) {
				return item.getBsebrid();
			}else if(StringUtils.isNotBlank(item.getAsebrid())) {
				return item.getAsebrid();
			}else if(StringUtils.isNotBlank(item.getStebrid())) {
				return item.getStebrid();
			}
			return null;
		}, item->item.getState()));
		if(CollectionUtils.isNotEmpty(ebmInfoDto.getEbrDtoList())) {
			ebmInfoDto.getEbrDtoList().forEach(ebrDto->{
//				ebrDto.setDispatchState((Integer)ebrStateMap.get(ebrDto.getEbrId()));
				ebrDto.setDispatchState(MapUtils.getInteger(ebrStateMap, ebrDto.getEbrId()));
			});
		}
		//查询区域名称
		String[] areacodes = bcEbm.getAreacode().split(",");
		Condition condition = new Condition(SysRegionArea.class);
		condition.createCriteria().andIn("areacode", Arrays.asList(areacodes));
		List<SysRegionArea> regionAreas = sysRegionAreaService.findByCondition(condition);
		if(CollectionUtils.isNotEmpty(regionAreas)){
			List<String> areaNames = regionAreas.stream().map(ele->ele.getAreaname()).collect(Collectors.toList());
			ebmInfoDto.setAreaNames(areaNames);
		}

		return ebmInfoDto;
	}

}
