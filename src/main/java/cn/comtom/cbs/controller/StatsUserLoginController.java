package cn.comtom.cbs.controller;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.dto.StatsUserDto;
import cn.comtom.cbs.model.StatsUserLogin;
import cn.comtom.cbs.service.StatsUserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * StatsUserLogin相关API。Created on 2018-12-30。
 * 
 * @author William
 */
@Api(value = "用户登录信息相关接口", tags = { "用户登录信息相关接口" })
@RestController
@RequestMapping("/stats/user")
public class StatsUserLoginController {
	@Resource
	private StatsUserLoginService statsUserLoginService;

	@PostMapping
	public Result<StatsUserLogin> add(@RequestBody StatsUserLogin statsUserLogin) {
		statsUserLoginService.save(statsUserLogin);
		return ResultGenerator.genSuccessResult(statsUserLogin);
	}

	@PostMapping("/batch")
	public Result<StatsUserLogin> batchAdd(@RequestParam int count) {
		statsUserLoginService.batchAdd(count);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping("/{id}")
	public Result<StatsUserLogin> delete(@PathVariable String id) {
		statsUserLoginService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result<StatsUserLogin> update(@RequestBody StatsUserLogin statsUserLogin) {
		statsUserLoginService.update(statsUserLogin);
		return ResultGenerator.genSuccessResult(statsUserLogin);
	}

	@GetMapping("/{id}")
	public Result<StatsUserLogin> detail(@PathVariable String id) {
		StatsUserLogin statsUserLogin = statsUserLoginService.findById(id);
		return ResultGenerator.genSuccessResult(statsUserLogin);
	}

	@GetMapping
	public Result<PageInfo<StatsUserLogin>> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<StatsUserLogin> list = statsUserLoginService.findAll();
		PageInfo<StatsUserLogin> pageInfo = new PageInfo<StatsUserLogin>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	/**
	 * 统计活跃用户数
	 * 
	 * @param page
	 * @param size
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @return
	 */
	@ApiOperation(value = "统计活跃用户数")
	@GetMapping("/summary")
	public Result<PageInfo<StatsUserDto>> userLoginSummary(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value="汇总使用的维度，可以是day, week, month, year ,clientType") @RequestParam(defaultValue = "") String sumType,
			@ApiParam(value = "区域ID") @RequestParam(defaultValue = "") String orgId,
			@ApiParam(value = "开始日期") @RequestParam(defaultValue = "") String beginDate,
			@ApiParam(value = "结束日期") @RequestParam(defaultValue = "") String endDate) {
		sumType = sumType.trim();
		orgId = orgId.trim();
		beginDate = beginDate.trim();
		endDate = endDate.trim();

		PageHelper.startPage(page, size);
		List<StatsUserDto> list = statsUserLoginService.summary(sumType, orgId, beginDate, endDate);
		PageInfo<StatsUserDto> pageInfo = new PageInfo<StatsUserDto>(list);

		return ResultGenerator.genSuccessResult(pageInfo);
	}
	/**
	 * 活跃用户总数
	 * 
	 * @param page
	 * @param size
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @return
	 */
	@ApiOperation(value = "统计活跃用户总数")
	@GetMapping("/total")
	public Result<PageInfo<StatsUserDto>> totalActiveUsers(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value="统计周期，可以是day,week,month,year") @RequestParam(defaultValue = "") String period,
			@ApiParam(value = "区域ID") @RequestParam(defaultValue = "") String orgId) {
		period = period.trim();
		orgId = orgId.trim();
		

		PageHelper.startPage(page, size);
		List<StatsUserDto> list = statsUserLoginService.total(period, orgId);
		PageInfo<StatsUserDto> pageInfo = new PageInfo<StatsUserDto>(list);

		return ResultGenerator.genSuccessResult(pageInfo);
	}

}
