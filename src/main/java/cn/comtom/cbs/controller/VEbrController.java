package cn.comtom.cbs.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.dto.ebm.EbrDto;
import cn.comtom.cbs.dto.ebm.EbrQueryRequest;
import cn.comtom.cbs.dto.ebr.EbrTypeDto;
import cn.comtom.cbs.service.IVEbrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ebr/count")
@Api(tags = "系统资源统计信息")
@Slf4j
public class VEbrController {

    @Autowired
    private IVEbrService ebrService;


    @GetMapping("/getPlatformEbrlist")
    @ApiOperation(value = "查询系统资源信息视图列表", notes = "查询系统资源信息视图列表")
    public Result<List<EbrDto>> list(){
    	try {
			EbrQueryRequest request = new EbrQueryRequest();
			List<EbrDto> list = ebrService.getPlatformEbrlist(request);
			return ResultGenerator.genSuccessResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("内部服务错误："+e.getMessage());
		}
    }

    
    @GetMapping("/typecount")
    @ApiOperation(value = "根据资源类型统计资源", notes = "根据资源类型统计资源")
    public Result<List<EbrTypeDto>> typecount(){
		try {
			List<EbrTypeDto> ebrTypeDto = ebrService.countEbrType();
			return ResultGenerator.genSuccessResult(ebrTypeDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("内部服务错误："+e.getMessage());
		}
    	
    } 

}
