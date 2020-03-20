/**
 * 
 */
package cn.comtom.cbs.dto;

import java.util.List;

import cn.comtom.cbs.model.Dashboard;
import cn.comtom.cbs.model.DashboardConfig;
import cn.comtom.cbs.model.DashboardTmpl;
import cn.comtom.cbs.model.DashboardTmplDetail;

/**
 * @author william
 *
 */
public class DashboardDto {
	private DashboardTmpl tmpl;
	private List<DashboardTmplDetail> tmplDetails;
	
	private Dashboard dashboard;
	private List<DashboardConfig> dashboardConfig;
	private List<ChartDataDto> charts;
	
	/**
	 * @return the charts
	 */
	public List<ChartDataDto> getCharts() {
		return charts;
	}
	/**
	 * @param charts the charts to set
	 */
	public void setCharts(List<ChartDataDto> charts) {
		this.charts = charts;
	}
	/**
	 * @return the tmpl
	 */
	public DashboardTmpl getTmpl() {
		return tmpl;
	}
	/**
	 * @param tmpl the tmpl to set
	 */
	public void setTmpl(DashboardTmpl tmpl) {
		this.tmpl = tmpl;
	}
	/**
	 * @return the tmplDetails
	 */
	public List<DashboardTmplDetail> getTmplDetails() {
		return tmplDetails;
	}
	/**
	 * @param tmplDetails the tmplDetails to set
	 */
	public void setTmplDetails(List<DashboardTmplDetail> tmplDetails) {
		this.tmplDetails = tmplDetails;
	}
	/**
	 * @return the dashboard
	 */
	public Dashboard getDashboard() {
		return dashboard;
	}
	/**
	 * @param dashboard the dashboard to set
	 */
	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	/**
	 * @return the dashboardConfig
	 */
	public List<DashboardConfig> getDashboardConfig() {
		return dashboardConfig;
	}
	/**
	 * @param dashboardConfig the dashboardConfig to set
	 */
	public void setDashboardConfig(List<DashboardConfig> dashboardConfig) {
		this.dashboardConfig = dashboardConfig;
	}

}
