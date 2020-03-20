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
import cn.comtom.cbs.model.User;
import cn.comtom.cbs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * User相关API。Created on 2018-12-28。
 * 
 * @author William
 */
@Api(value = "用户相关接口", tags= {"用户相关接口"} )
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@PostMapping
	public Result<User> add(@RequestBody User user) {
		userService.save(user);
		return ResultGenerator.genSuccessResult(user);
	}

	@DeleteMapping("/{id}")
	public Result<User> delete(@PathVariable String id) {
		userService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result<User> update(@RequestBody User user) {
		userService.update(user);
		return ResultGenerator.genSuccessResult(user);
	}

	@GetMapping("/{id}")
	public Result<User> detail(@PathVariable String id) {
		User user = userService.findById(id);
		return ResultGenerator.genSuccessResult(user);
	}

	@GetMapping
	public Result<PageInfo<User>> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<User> list = userService.findAll();
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}
	@ApiOperation(value = "获取用户统计信息，主要是不同维度下的用户数量，并按区域过滤")
	@GetMapping("/summary")
	public Result<PageInfo<StatsUserDto>> summary(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value="汇总使用的维度，可以是time") @RequestParam(defaultValue = "") String sumType,
			@ApiParam(value = "父区域ID") @RequestParam(defaultValue = "") String orgId) {
		sumType = sumType.trim();
		orgId = orgId.trim();
		
		PageHelper.startPage(page, size);
		List<StatsUserDto> list = userService.summary(sumType, orgId);
		PageInfo<StatsUserDto> pageInfo = new PageInfo<StatsUserDto>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
		
	}
	/**
	 * 用户数量统计
	 * 
	 * @param page
	 * @param size
	 * @param beginDate
	 * @param endDate
	 * @param orgId
	 * @param period
	 * @return
	 */
	@ApiOperation(value = "按周期统计用户数量，可以按日期和区域过滤")
	@GetMapping("/audienceSummary")
	public Result<PageInfo<StatsUserDto>> audienceSummary(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value = "开始日期") @RequestParam(defaultValue = "") String beginDate,
			@ApiParam(value = "结束日期") @RequestParam(defaultValue = "") String endDate,
			@ApiParam(value = "区域ID") @RequestParam(defaultValue = "") String orgId,
			@ApiParam(value = "统计周期") @RequestParam(defaultValue = "") String period) {
		beginDate = beginDate.trim();
		endDate = endDate.trim();
		orgId = orgId.trim();
		period = period.trim();

		List<StatsUserDto> list = userService.findByPeriod(beginDate, endDate, orgId, period);

		PageHelper.startPage(page, size);
		PageInfo<StatsUserDto> pageInfo = new PageInfo<StatsUserDto>(list);

		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@ApiOperation(value = "按周期统计新增用户数，可以按日期和区域过滤")
	@GetMapping("/increaseUserSummary")
	public Result<PageInfo<StatsUserDto>> increaseUserSummary(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value = "开始日期") @RequestParam(defaultValue = "") String beginDate,
			@ApiParam(value = "结束日期") @RequestParam(defaultValue = "") String endDate,
			@ApiParam(value = "区域ID") @RequestParam(defaultValue = "") String orgId,
			@ApiParam(value = "统计周期") @RequestParam(defaultValue = "") String period) {
		beginDate = beginDate.trim();
		endDate = endDate.trim();
		orgId = orgId.trim();
		period = period.trim();

		List<StatsUserDto> list = userService.findByIncreaseUserPeriod(beginDate, endDate, orgId, period);

		PageHelper.startPage(page, size);
		PageInfo<StatsUserDto> pageInfo = new PageInfo<StatsUserDto>(list);

		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
