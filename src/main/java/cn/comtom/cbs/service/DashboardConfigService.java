package cn.comtom.cbs.service;
import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.model.DashboardConfig;


/**
 * DashboardConfig Service接口，Created on 2019-01-02.
 * @author William 
 */
public interface DashboardConfigService extends Service<DashboardConfig> {
	/**
	 * 根据Dashboard ID 查找并以blockIndex升序排列返回的记录列表
	 * 
	 * @param dashboardId
	 */
	List<DashboardConfig> findByDashboardIdAndAscByBlockIndex(int dashboardId);
}
