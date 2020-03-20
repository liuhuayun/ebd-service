package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.BcEbmBrdRecordMapper;
import cn.comtom.cbs.model.BcEbmBrdRecord;
import cn.comtom.cbs.service.BcEbmBrdRecordService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * BcEbmBrdRecord Service实现类，Created on 2019-06-19.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BcEbmBrdRecordServiceImpl extends AbstractService<BcEbmBrdRecord> implements BcEbmBrdRecordService {
    @Resource
    private BcEbmBrdRecordMapper bcEbmBrdRecordMapper;

}
