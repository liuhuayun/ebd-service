/**
 * 
 */
package cn.comtom.cbs.dto;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

/**
 * @author william
 *
 */
@Data
public class StatsUserDto implements Serializable {
	private static final long serialVersionUID = 1001L;
	/**
	 * 可以是日、周、月
	 */
	@Column(name = "time")
	private String time;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "week")
	private String week;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "total")
	private int total;
	
	 /**
     * 客户端类型(WEB/IOS/ANDROID)
     */
    @Column(name = "client_type")
    private String clientType;
}
