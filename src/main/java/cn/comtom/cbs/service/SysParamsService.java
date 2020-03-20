package cn.comtom.cbs.service;
import cn.comtom.cbs.model.SysParams;
import cn.comtom.cbs.core.Service;


/**
 * SysParams Service接口，Created on 2019-08-13.
 * @author liuhy 
 */
public interface SysParamsService extends Service<SysParams> {
	
	String getValByKey(String key);
}
