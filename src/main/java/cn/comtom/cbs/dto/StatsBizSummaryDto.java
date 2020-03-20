/**
 * 
 */
package cn.comtom.cbs.dto;

import javax.persistence.Column;

import cn.comtom.cbs.model.StatsBizSummary;

/**
 * @author william
 *
 */
public class StatsBizSummaryDto extends StatsBizSummary {
	@Column(name = "day")
	private String day;

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

}
