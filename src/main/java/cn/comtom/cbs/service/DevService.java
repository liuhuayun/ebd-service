package cn.comtom.cbs.service;
import cn.comtom.cbs.model.Dev;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.dto.DevDto;


/**
 * Dev Service接口，Created on 2018-12-28.
 * @author William 
 */
public interface DevService extends Service<Dev> {
	/**
	 * 批量添加数据
	 * 
	 * @param count  要添加的记录条数
	 */
	void batchAdd(int count);
	
	/**
	 * 设备信息汇总
	 * 
	 * @param sumType  汇总类型，可以是设备类型、时间
	 * @param parentOrgId 区域ID。根据区域ID对设备进行过滤
	 * @return list 设备汇总信息
	 */
	List<DevDto> summary(String sumType, String parentOrgId);

	/**
	 * 设备总量
	 * 
	 * @param sumType  汇总类型，可以是设备类型、时间
	 * @param parentOrgId 区域ID。根据区域ID对设备进行过滤
	 * @return list 设备汇总信息
	 */
	List<DevDto> total(String sumType, String parentOrgId);
	
	Condition createGetDevListCondition(String onlineStatus, String bizStatus, String orgId);
}
