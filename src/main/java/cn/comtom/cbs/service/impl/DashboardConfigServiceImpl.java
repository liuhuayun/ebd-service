package cn.comtom.cbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.comtom.cbs.core.AbstractService;
import cn.comtom.cbs.dao.DashboardConfigMapper;
import cn.comtom.cbs.model.DashboardConfig;
import cn.comtom.cbs.service.DashboardConfigService;


/**
 * DashboardConfig Service实现类，Created on 2019-01-02.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DashboardConfigServiceImpl extends AbstractService<DashboardConfig> implements DashboardConfigService {
    @Resource
    private DashboardConfigMapper tDashboardConfigMapper;
    @Override
	public List<DashboardConfig> findByDashboardIdAndAscByBlockIndex(int dashboardId) {
		return tDashboardConfigMapper.findByDashboardIdAndAscByBlockIndex(dashboardId);
	}
}
