package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcEbmDispatchMapper;
import cn.comtom.cbs.model.BcEbmDispatch;
import cn.comtom.cbs.service.BcEbmDispatchService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcEbmDispatch Service实现类，Created on 2019-08-23.
 * @author liuhy
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbmDispatchServiceImpl extends AbstractService<BcEbmDispatch> implements BcEbmDispatchService {
    @Resource
    private BcEbmDispatchMapper bcEbmDispatchMapper;

}
