package cn.comtom.cbs.service;
import cn.comtom.cbs.model.DashboardTmpl;
import cn.comtom.cbs.core.Service;


/**
 * DashboardTmpl Service接口，Created on 2019-01-02.
 * @author William 
 */
public interface DashboardTmplService extends Service<DashboardTmpl> {
	
	int insert(DashboardTmpl dashboardTmpl);

}
