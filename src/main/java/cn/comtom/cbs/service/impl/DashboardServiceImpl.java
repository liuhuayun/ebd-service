package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.DashboardMapper;
import cn.comtom.cbs.model.Dashboard;
import cn.comtom.cbs.service.DashboardService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Dashboard Service实现类，Created on 2019-01-02.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DashboardServiceImpl extends AbstractService<Dashboard> implements DashboardService {
    @Resource
    private DashboardMapper tDashboardMapper;

}
