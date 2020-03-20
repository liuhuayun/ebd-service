package cn.comtom.cbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.dto.StatsUserDto;
import cn.comtom.cbs.model.StatsUserLogin;

/**
 * 
 * @author gege
 *
 */
public interface StatsUserLoginMapper extends Mapper<StatsUserLogin> {
	/**
	 * 批量添加数据
	 * 
	 * @param map
	 * @return
	 */
	@Insert("${sql}")
	void batchAdd(Map<String, String> map);

	/**
	 * 统计每天登录的用户数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @return
	 */
	List<StatsUserDto> findByDay(@Param("beginDate") String beginDate, @Param("endDate") String endDate,
			@Param("orgId") String orgId);

	@Results({ @Result(property = "clientType", column = "client_type") })
	@Select("${sql}")
	List<StatsUserDto> summary(Map<String, String> map);
}