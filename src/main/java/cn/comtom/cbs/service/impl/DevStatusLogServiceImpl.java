package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.DevStatusLogMapper;
import cn.comtom.cbs.model.DevStatusLog;
import cn.comtom.cbs.service.DevStatusLogService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * DevStatusLog Service实现类，Created on 2018-12-28.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DevStatusLogServiceImpl extends AbstractService<DevStatusLog> implements DevStatusLogService {
    @Resource
    private DevStatusLogMapper tDevStatusLogMapper;

}
