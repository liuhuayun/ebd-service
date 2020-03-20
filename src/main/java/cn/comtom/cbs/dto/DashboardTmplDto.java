/**
 * 
 */
package cn.comtom.cbs.dto;

import java.util.List;

import cn.comtom.cbs.model.DashboardTmpl;
import cn.comtom.cbs.model.DashboardTmplDetail;

/**
 * @author william
 *
 */
public class DashboardTmplDto {
	private DashboardTmpl tmpl;
	private List<DashboardTmplDetail> tmplDetails;
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
}
