package cn.comtom.cbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.dto.DevDto;
import cn.comtom.cbs.model.Dev;

/**
 * Created by Mybatis Generator on 2018/12/28
 * 
 * @author William
 */
public interface DevMapper extends Mapper<Dev> {
	/**
	 * 批量添加数据
	 * 
	 * @param map
	 * @return
	 */
	@Insert("${sql}")
	void batchAdd(Map<String, String> map);
	/**
	 * 获取设备汇总信息
	 * 
	 * @param map
	 *            SQL string
	 * @return list 汇总信息列表
	 */
	@Results({ @Result(property = "bindDate", column = "bind_date"),
			@Result(property = "devType", column = "dev_type"),
			@Result(property = "onlineStatus", column = "online_status"),
			@Result(property = "bizStatus", column = "biz_status")})
	@Select("${sql}")
	List<DevDto> summary(Map<String, String> map);
}