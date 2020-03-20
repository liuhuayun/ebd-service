package cn.comtom.cbs.dao;

import cn.comtom.cbs.core.Mapper;
import cn.comtom.cbs.dto.ebm.EbrDto;
import cn.comtom.cbs.model.BcEbrPlatform;

public interface BcEbrPlatformMapper extends Mapper<BcEbrPlatform> {
	
	EbrDto getEbrInfoById(String ebrId);
}