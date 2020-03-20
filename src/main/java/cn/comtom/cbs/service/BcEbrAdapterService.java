package cn.comtom.cbs.service;
import cn.comtom.cbs.model.BcEbrAdapter;
import cn.comtom.cbs.core.Service;


/**
 * BcEbrAdapter Service接口，Created on 2019-06-19.
 * @author William 
 */
public interface BcEbrAdapterService extends Service<BcEbrAdapter> {

	long countByState(String thisEbrId, String string);

}
