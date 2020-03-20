package cn.comtom.cbs.service;
import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.dto.StatsUserDto;
import cn.comtom.cbs.model.User;


/**
 * User Service接口，Created on 2018-12-28.
 * @author William 
 */
public interface UserService extends Service<User> {
	
	/**
	 * 根据用户名查找用户信息
	 * @param username
	 * @return
	 */
	User findByUserName(String username);
	
	/**
	 * 用户信息汇总
	 * 
	 * @param sumType  汇总类型，可以是时间
	 * @param parentOrgId 区域ID。根据区域ID对设备进行过滤
	 * @return list 用户汇总信息
	 */
	List<StatsUserDto> summary(String sumType, String parentOrgId);

	/**
	 * 用户总数
	 * 
	 * @param sumType  汇总类型，可以是时间
	 * @param parentOrgId 区域ID。根据区域ID对设备进行过滤
	 * @return list 用户总数
	 */
	List<StatsUserDto> total(String sumType, String parentOrgId);
	/**
	 * 根据周期查询用户状态进行统计
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @param period
	 * @return
	 */
	List<StatsUserDto> findByPeriod(String beginDate, String endDate, String orgId, String period);

	/**
	 * 根据周对新增用户数量进行统计
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @param period
	 * @return
	 */
	List<StatsUserDto> findByIncreaseUserPeriod(String beginDate, String endDate, String orgId, String period);
}
