package cn.comtom.cbs.service;
import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.dto.StatsUserDto;
import cn.comtom.cbs.model.StatsUserLogin;


/**
 * StatsUserLogin Service接口，Created on 2018-12-30.
 * @author William 
 */
public interface StatsUserLoginService extends Service<StatsUserLogin> {
	/**
	 * 统计每天登录的用户数
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @return
	 */

	List<StatsUserDto> findByDay(String beginDate, String endDate, String orgId);
	
	/**
	 * 批量添加数据
	 * 
	 * @param count  要添加的记录条数
	 */
	void batchAdd(int count);
	
	/**
	 * 活跃用户数汇总
	 * 
	 * @param sumType  汇总类型，可以是设备类型、时间
	 * @param parentOrgId 区域ID。根据区域ID对设备进行过滤
	 * @return list 活跃用户汇总信息
	 */
	List<StatsUserDto> summary(String sumType, String parentOrgId, String beginDate, String endDate);

	/**
	 * 统计当日、本周、本月活跃用户数
	 * 
	 * @param sumType  汇总类型，可以是设备类型、时间
	 * @param parentOrgId 区域ID。根据区域ID对设备进行过滤
	 * @return list 设备汇总信息
	 */
	List<StatsUserDto> total(String period, String parentOrgId);
}
