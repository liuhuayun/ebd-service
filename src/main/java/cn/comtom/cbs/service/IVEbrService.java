package cn.comtom.cbs.service;

import java.util.List;

import cn.comtom.cbs.core.Service;
import cn.comtom.cbs.dto.ebm.EbrDto;
import cn.comtom.cbs.dto.ebm.EbrQueryRequest;
import cn.comtom.cbs.dto.ebr.EbrTypeDto;
import cn.comtom.cbs.model.VEbr;

public interface IVEbrService extends Service<VEbr>  {

	List<EbrDto> getPlatformEbrlist(EbrQueryRequest request);
	 
    List<EbrDto> list(EbrQueryRequest request);

	List<EbrTypeDto> countEbrType();

}
