package cn.comtom.cbs.service.impl;

import cn.comtom.cbs.dao.DashboardTmplMapper;
import cn.comtom.cbs.model.DashboardTmpl;
import cn.comtom.cbs.service.DashboardTmplService;
import cn.comtom.cbs.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * DashboardTmpl Service实现类，Created on 2019-01-02.
 * @author William
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DashboardTmplServiceImpl extends AbstractService<DashboardTmpl> implements DashboardTmplService {
    @Resource
    private DashboardTmplMapper tDashboardTmplMapper;

	@Override
	public int insert(DashboardTmpl dashboardTmpl) {
		return tDashboardTmplMapper.insert(dashboardTmpl);
	}

}
