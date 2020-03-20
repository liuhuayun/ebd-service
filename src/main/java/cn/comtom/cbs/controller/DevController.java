package cn.comtom.cbs.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.dto.DevDto;
import cn.comtom.cbs.model.Dev;
import cn.comtom.cbs.model.StatsUserLogin;
import cn.comtom.cbs.service.DevService;
import cn.comtom.cbs.service.OrgPeersService;
import cn.comtom.cbs.service.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import tk.mybatis.mapper.entity.Condition;

/**
 * Dev相关API。Created on 2018-12-28。
 * 
 * @author William
 */
@Api(value = "设备信息相关接口", tags= {"设备信息相关接口"})
@RestController
@RequestMapping("/dev")
public class DevController {
	@Resource
	private DevService devService;

	@Resource
	private OrgPeersService orgPeersService;

	private void sendMessage(Dev dev) {		
		try {
			WebSocket.sendJson("dev", JSON.toJSONString(dev));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@PostMapping
	public Result<Dev> add(@RequestBody Dev dev) {
		devService.save(dev);
		this.sendMessage(dev);
		return ResultGenerator.genSuccessResult(dev);
	}
	
	@PostMapping("/batch")
	public Result<StatsUserLogin> batchAdd(@RequestParam int count) {
		devService.batchAdd(count);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping("/{id}")
	public Result<Dev> delete(@PathVariable String id) {
		devService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result<Dev> update(@RequestBody Dev dev) {
		devService.update(dev);
		this.sendMessage(dev);
		return ResultGenerator.genSuccessResult(dev);
	}

	@GetMapping("/{id}")
	public Result<Dev> detail(@PathVariable String id) {
		Dev dev = devService.findById(id);
		return ResultGenerator.genSuccessResult(dev);
	}

	@ApiOperation(value = "按查询条件获取设备列表。查询条件可以是设备状态或者区域ID")
	@GetMapping
	public Result<PageInfo<Dev>> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value = "是否在线") @RequestParam(defaultValue = "") String onlineStatus,
			@ApiParam(value = "在线状态类型") @RequestParam(defaultValue = "") String bizStatus,
			@ApiParam(value = "父区域ID") @RequestParam(defaultValue = "") String orgId) {
		
		Condition condition = devService.createGetDevListCondition(onlineStatus, bizStatus, orgId);
		
		PageHelper.startPage(page, size);
		List<Dev> list = devService.findByCondition(condition);
		PageInfo<Dev> pageInfo = new PageInfo<Dev>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}
	
	@ApiOperation(value = "获取设备统计信息，主要是不同维度下的设备数量，并按区域过滤")
	@GetMapping("/summary")
	public Result<PageInfo<DevDto>> summary(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value="汇总使用的维度，可以是date,type,status") @RequestParam(defaultValue = "") String sumType,
			@ApiParam(value = "父区域ID") @RequestParam(defaultValue = "") String orgId) {
		sumType = sumType.trim();
		orgId = orgId.trim();
		List<DevDto> list = devService.summary(sumType, orgId);

		PageHelper.startPage(page, size);
		PageInfo<DevDto> pageInfo = new PageInfo<DevDto>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
		
	}
	@ApiOperation(value = "获取按时间分布的设备总量，可以按设备状态或者类型分组，并按区域和日期过滤")
	@GetMapping("/total")
	public Result<PageInfo<DevDto>> total(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value="汇总使用的维度，可以是type,status") @RequestParam(defaultValue = "") String sumType,
			@ApiParam(value = "父区域ID") @RequestParam(defaultValue = "") String orgId) {
		sumType = sumType.trim();
		orgId = orgId.trim();
		List<DevDto> list = devService.total(sumType, orgId);

		PageHelper.startPage(page, size);
		PageInfo<DevDto> pageInfo = new PageInfo<DevDto>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
		
	}
}
