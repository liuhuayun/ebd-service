package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.StatsSecuritySummaryMapper;
import cn.comtom.cbs.model.StatsSecuritySummary;
import cn.comtom.cbs.service.StatsSecuritySummaryService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * StatsSecuritySummary Service实现类，Created on 2018-12-31.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StatsSecuritySummaryServiceImpl extends AbstractService<StatsSecuritySummary> implements StatsSecuritySummaryService {
    @Resource
    private StatsSecuritySummaryMapper statsSecuritySummaryMapper;

}
