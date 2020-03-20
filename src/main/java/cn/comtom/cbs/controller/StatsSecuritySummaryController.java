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
import cn.comtom.cbs.model.StatsSecuritySummary;
import cn.comtom.cbs.service.StatsSecuritySummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Condition;

/**
 * StatsSecuritySummary相关API。Created on 2018-12-31。
 * 
 * @author William
 */
@Api(value = "安全状态相关接口", tags= {"安全状态相关接口"})
@RestController
@RequestMapping("/stats/security/summary")
public class StatsSecuritySummaryController {
	@Resource
	private StatsSecuritySummaryService statsSecuritySummaryService;

	@PostMapping
	public Result<StatsSecuritySummary> add(@RequestBody StatsSecuritySummary statsSecuritySummary) {
		statsSecuritySummaryService.save(statsSecuritySummary);
		return ResultGenerator.genSuccessResult(statsSecuritySummary);
	}

	@DeleteMapping("/{id}")
	public Result<StatsSecuritySummary> delete(@PathVariable String id) {
		statsSecuritySummaryService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result<StatsSecuritySummary> update(@RequestBody StatsSecuritySummary statsSecuritySummary) {
		statsSecuritySummaryService.update(statsSecuritySummary);
		return ResultGenerator.genSuccessResult(statsSecuritySummary);
	}

	@GetMapping("/{id}")
	public Result<StatsSecuritySummary> detail(@PathVariable String id) {
		StatsSecuritySummary statsSecuritySummary = statsSecuritySummaryService.findById(id);
		return ResultGenerator.genSuccessResult(statsSecuritySummary);
	}

	@GetMapping
	public Result<PageInfo<StatsSecuritySummary>> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<StatsSecuritySummary> list = statsSecuritySummaryService.findAll();
		PageInfo<StatsSecuritySummary> pageInfo = new PageInfo<StatsSecuritySummary>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@ApiOperation(value = "获取最新的安全状态")
	@GetMapping("/latest")
	public Result<StatsSecuritySummary> latest() {

		Condition condition = new Condition(StatsSecuritySummary.class);
		condition.setOrderByClause("scan_time desc" + " limit 1");

		StatsSecuritySummary statsSecuritySummary = (statsSecuritySummaryService.findByCondition(condition)).get(0);
		return ResultGenerator.genSuccessResult(statsSecuritySummary);
	}

}
