/**
 * 
 */
package cn.comtom.cbs.dto;

import cn.comtom.cbs.model.DashboardConfig;

/**
 * @author william
 *
 */
public class DashboardConfigDto {
	DashboardConfig dashboardConfig;
	private ChartDataDto chart;
	/**
	 * @return the dashboardConfig
	 */
	public DashboardConfig getDashboardConfig() {
		return dashboardConfig;
	}
	/**
	 * @param dashboardConfig the dashboardConfig to set
	 */
	public void setDashboardConfig(DashboardConfig dashboardConfig) {
		this.dashboardConfig = dashboardConfig;
	}
	/**
	 * @return the chart
	 */
	public ChartDataDto getChart() {
		return chart;
	}
	/**
	 * @param chart the chart to set
	 */
	public void setChart(ChartDataDto chart) {
		this.chart = chart;
	}
}
