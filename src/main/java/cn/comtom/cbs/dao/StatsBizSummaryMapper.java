package cn.comtom.cbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.dto.StatsBizSummaryDto;
import cn.comtom.cbs.model.StatsBizSummary;

/**
 * 
 * @author gege
 *
 */
public interface StatsBizSummaryMapper extends Mapper<StatsBizSummary> {
	/**
	 * 批量添加数据
	 * 
	 * @param map
	 * @return
	 */
	@Insert("${sql}")
	void batchAdd(Map<String, String> map);
	/**
	 * 业务信息统计
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @return
	 */
	List<StatsBizSummaryDto> findByDayBizStatus(@Param("beginDate")String beginDate, 
			@Param("endDate")String endDate, 
			@Param("orgId")String orgId);
}