package cn.comtom.cbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.dto.StatsBizAbstractDto;
import cn.comtom.cbs.model.StatsBizAbstract;

/**
 * 
 * @author gege
 *
 */
public interface StatsBizAbstractMapper extends Mapper<StatsBizAbstract> {

	/**
	 * 业务动态查询
	 * 
	 * @param date
	 * @param orgId
	 * @return
	 */
	@Results({ @Result(property = "orgName", column = "org_name"),
		@Result(property = "bizType", column = "biz_type"),
		@Result(property = "bizTime", column = "biz_time"),
		@Result(property = "bizAbstract", column = "biz_abstract"),
		@Result(property = "opUser", column = "op_user"),
		@Result(property = "orgId", column = "org_id")})
	@Select("${sql}")
	List<StatsBizAbstractDto> findByDay(Map<String, String> map);
}