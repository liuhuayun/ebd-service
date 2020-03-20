/**
 * 
 */
package cn.comtom.cbs.dto;

import java.io.Serializable;

import javax.persistence.Column;

import cn.comtom.cbs.model.StatsBizAbstract;

/**
 * @author william
 *
 */
public class StatsBizAbstractDto extends StatsBizAbstract implements Serializable {
	private static final long serialVersionUID = 10003L;

	@Column(name = "org_name")
	private String orgName;

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
