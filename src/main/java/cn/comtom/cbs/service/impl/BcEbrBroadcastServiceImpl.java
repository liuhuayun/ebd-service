package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcEbrBroadcastMapper;
import cn.comtom.cbs.model.BcEbrBroadcast;
import cn.comtom.cbs.service.BcEbrBroadcastService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcEbrBroadcast Service实现类，Created on 2019-06-19.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbrBroadcastServiceImpl extends AbstractService<BcEbrBroadcast> implements BcEbrBroadcastService {
    @Resource
    private BcEbrBroadcastMapper bcEbrBroadcastMapper;

}
