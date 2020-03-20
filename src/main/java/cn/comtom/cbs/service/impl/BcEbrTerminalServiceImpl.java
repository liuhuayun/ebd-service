package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcEbrTerminalMapper;
import cn.comtom.cbs.model.BcEbrTerminal;
import cn.comtom.cbs.service.BcEbrTerminalService;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.comtom.cbs.core.AbstractService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcEbrTerminal Service实现类，Created on 2019-08-14.
 * @author liuhy
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbrTerminalServiceImpl extends AbstractService<BcEbrTerminal> implements BcEbrTerminalService {
	
    @Resource
    private BcEbrTerminalMapper bcEbrTerminalMapper;
    
    public long countByState(String psEbrId, String state) {
    	Condition condition = new Condition(BcEbrTerminal.class);
    	Criteria criteria = condition.createCriteria();
    	if(StringUtils.isNotBlank(psEbrId)) {
    		criteria.andEqualTo("relatedpsebrid",psEbrId);
    	}
    	if(StringUtils.isNotBlank(state)) {
    		criteria.andEqualTo("terminalstate",state);
    	}
    	int count = bcEbrTerminalMapper.selectCountByCondition(condition);
    	return count;
    }

}
