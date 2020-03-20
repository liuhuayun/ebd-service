package cn.comtom.cbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.model.DashboardConfig;

/**
* Created by Mybatis Generator on 2019/01/01
* @author William
*/
public interface DashboardConfigMapper extends Mapper<DashboardConfig> {
	/**
	 * 根据Dashboard ID 查找并以blockIndex升序排列返回的记录列表
	 * 
	 * @param dashboardId
	 * @return List
	 */
	List<DashboardConfig> findByDashboardIdAndAscByBlockIndex(@Param("dashboardId")int dashboardId);
}