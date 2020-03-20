/**
 * 
 */
package cn.comtom.cbs.dto;

import java.io.Serializable;

import javax.persistence.Column;

import cn.comtom.cbs.model.Dev;

/**
 * @author william
 *
 */
public class DevDto extends Dev implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "total")
	private int total;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "bind_date")
	private String bindDate;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the bindDate
	 */
	public String getBindDate() {
		return bindDate;
	}

	/**
	 * @param bindDate the bindDate to set
	 */
	public void setBindDate(String bindDate) {
		this.bindDate = bindDate;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

}
