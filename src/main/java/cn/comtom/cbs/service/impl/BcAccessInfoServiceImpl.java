package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcAccessInfoMapper;
import cn.comtom.cbs.model.BcAccessInfo;
import cn.comtom.cbs.service.BcAccessInfoService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcAccessInfo Service实现类，Created on 2019-06-19.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcAccessInfoServiceImpl extends AbstractService<BcAccessInfo> implements BcAccessInfoService {
    @Resource
    private BcAccessInfoMapper bcAccessInfoMapper;

}
