package cn.comtom.cbs.service;
import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.dto.StatsBizSummaryDto;
import cn.comtom.cbs.model.StatsBizSummary;


/**
 * StatsBizSummary Service接口，Created on 2018-12-31.
 * @author William 
 */
public interface StatsBizSummaryService extends Service<StatsBizSummary> {
	/**
	 * 批量添加数据
	 * 
	 * @param count  要添加的记录条数
	 */
	void batchAdd(int count);

	/**
	 * 业务信息统计
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @return
	 */
	List<StatsBizSummaryDto> findByDayBizStatus(String beginDate, String endDate, String orgId);

}
