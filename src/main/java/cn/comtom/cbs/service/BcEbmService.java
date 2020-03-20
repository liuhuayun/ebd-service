package cn.comtom.cbs.service;
import cn.comtom.cbs.model.BcEbm;

import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.dto.ebm.EbmInfoAllDto;


/**
 * BcEbm Service接口，Created on 2019-06-19.
 * @author William 
 */
public interface BcEbmService extends Service<BcEbm> {

	List<BcEbm> queryListLimit(Integer limit);

	EbmInfoAllDto findEbmInfoAll(String ebmId);

}
