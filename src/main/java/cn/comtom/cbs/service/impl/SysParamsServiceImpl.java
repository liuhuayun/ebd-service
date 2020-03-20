package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.SysParamsMapper;
import cn.comtom.cbs.model.SysParams;
import cn.comtom.cbs.service.SysParamsService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * SysParams Service实现类，Created on 2019-08-13.
 * @author liuhy
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysParamsServiceImpl extends AbstractService<SysParams> implements SysParamsService {
	
    @Resource
    private SysParamsMapper sysParamsMapper;
    
    @Override
    public String getValByKey(String key) {
    	SysParams condition = new SysParams();
    	condition.setParamKey(key);
    	SysParams result = sysParamsMapper.selectOne(condition);
    	return result==null?"":result.getParamValue();
    }

}
