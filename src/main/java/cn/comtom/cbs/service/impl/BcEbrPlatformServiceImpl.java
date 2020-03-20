package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcEbrPlatformMapper;
import cn.comtom.cbs.model.BcEbrPlatform;
import cn.comtom.cbs.service.BcEbrPlatformService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcEbrPlatform Service实现类，Created on 2019-06-19.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbrPlatformServiceImpl extends AbstractService<BcEbrPlatform> implements BcEbrPlatformService {
    @Resource
    private BcEbrPlatformMapper bcEbrPlatformMapper;

}
