package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcEbrAdapterMapper;
import cn.comtom.cbs.model.BcEbrAdapter;
import cn.comtom.cbs.model.BcEbrTerminal;
import cn.comtom.cbs.service.BcEbrAdapterService;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.comtom.cbs.core.AbstractService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcEbrAdapter Service实现类，Created on 2019-06-19.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbrAdapterServiceImpl extends AbstractService<BcEbrAdapter> implements BcEbrAdapterService {
	
    @Resource
    private BcEbrAdapterMapper bcEbrAdapterMapper;

	@Override
	public long countByState(String psEbrId, String state) {
		Condition condition = new Condition(BcEbrAdapter.class);
		Criteria criteria = condition.createCriteria();
    	if(StringUtils.isNotBlank(psEbrId)) {
    		criteria.andEqualTo("ebrpsid",psEbrId);
    	}
    	if(StringUtils.isNotBlank(state)) {
    		criteria.andEqualTo("adapterstate",state);
    	}
    	int count = bcEbrAdapterMapper.selectCountByCondition(condition);
    	return count;
	}

}
