package cn.comtom.cbs.service;
import cn.comtom.cbs.model.DashboardTmplDetail;

import java.util.List;

import cn.comtom.cbs.core.Service;


/**
 * DashboardTmplDetail Service接口，Created on 2019-01-02.
 * @author William 
 */
public interface DashboardTmplDetailService extends Service<DashboardTmplDetail> {
	/**
	 * 批量添加数据
	 * 
	 * @param list  对象列表
	 */
	void batchAdd(List<DashboardTmplDetail> list);
}
