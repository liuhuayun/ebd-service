package cn.comtom.cbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.dto.StatsUserDto;
import cn.comtom.cbs.model.User;

/**
* Created by Mybatis Generator on 2018/12/28
* @author William
*/
public interface UserMapper extends Mapper<User> {
	/**
	 * 获取用户汇总信息
	 * 
	 * @param map
	 *            SQL string
	 * @return list 汇总信息列表
	 */
	@Select("${sql}")
	List<StatsUserDto> summary(Map<String, String> map);
	/**
	 * 根据日期进行统计
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @param period
	 * @return
	 */
	List<StatsUserDto> findByPeriod(@Param("beginDate")String beginDate, 
			@Param("endDate")String endDate,
			@Param("orgId")String orgId, 
			@Param("period")String period);

	/**
	 * 根据周对新增用户数量进行统计
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @param period
	 * @return
	 */
	List<StatsUserDto> findByIncreaseUserPeriod(@Param("beginDate")String beginDate, 
			@Param("endDate")String endDate,
			@Param("orgId")String orgId, 
			@Param("period")String period);
	
	User findByUserName(@Param("username")String username);
}