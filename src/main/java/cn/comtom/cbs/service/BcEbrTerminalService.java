package cn.comtom.cbs.service;
import cn.comtom.cbs.model.BcEbrTerminal;
import cn.comtom.cbs.core.Service;


/**
 * BcEbrTerminal Service接口，Created on 2019-08-14.
 * @author liuhy 
 */
public interface BcEbrTerminalService extends Service<BcEbrTerminal> {
	
	 long countByState(String psEbrId, String state);
}
