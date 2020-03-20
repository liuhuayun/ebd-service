package cn.comtom.cbs.service;
import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.dto.StatsBizAbstractDto;
import cn.comtom.cbs.model.StatsBizAbstract;


/**
 * StatsBizAbstract Service接口，Created on 2018-12-31.
 * @author William 
 */
public interface StatsBizAbstractService extends Service<StatsBizAbstract> {

	/**
	 * 业务动态查询
	 * @param date
	 * @param orgId
	 * @return
	 */
	List<StatsBizAbstractDto> findByDay(String date, String orgId);

}
