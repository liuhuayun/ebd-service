package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcEbrStationMapper;
import cn.comtom.cbs.model.BcEbrStation;
import cn.comtom.cbs.service.BcEbrStationService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcEbrStation Service实现类，Created on 2019-06-19.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbrStationServiceImpl extends AbstractService<BcEbrStation> implements BcEbrStationService {
    @Resource
    private BcEbrStationMapper bcEbrStationMapper;

}
