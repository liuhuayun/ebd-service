package cn.comtom.cbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.OperatorLogMapper;
import cn.comtom.cbs.model.OperatorLog;
import cn.comtom.cbs.service.OperatorLogService;


/**
 * OperatorLog Service实现类，Created on 2018-12-28.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperatorLogServiceImpl extends AbstractService<OperatorLog> implements OperatorLogService {
    @Resource
    private OperatorLogMapper tOperatorLogMapper;


}
