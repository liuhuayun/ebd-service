package cn.comtom.cbs.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.model.DashboardTmplDetail;

/**
* Created by Mybatis Generator on 2019/01/01
* @author William
*/
public interface DashboardTmplDetailMapper extends Mapper<DashboardTmplDetail> {
	/**
	 * 批量添加数据
	 * 
	 * @param map
	 * @return
	 */
	@Insert("${sql}")
	void batchAdd(Map<String, String> map);
}