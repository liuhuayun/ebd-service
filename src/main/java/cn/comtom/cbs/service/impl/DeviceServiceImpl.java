package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.DeviceMapper;
import cn.comtom.cbs.model.Device;
import cn.comtom.cbs.service.DeviceService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Device Service实现类，Created on 2019-06-19.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl extends AbstractService<Device> implements DeviceService {
    @Resource
    private DeviceMapper deviceMapper;

}
