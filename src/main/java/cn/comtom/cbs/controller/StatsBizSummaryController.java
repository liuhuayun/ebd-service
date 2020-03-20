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
import cn.comtom.cbs.dto.StatsBizSummaryDto;
import cn.comtom.cbs.model.StatsBizSummary;
import cn.comtom.cbs.model.StatsUserLogin;
import cn.comtom.cbs.service.StatsBizSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * StatsBizSummary相关API。Created on 2018-12-31。
 * 
 * @author William
 */
@Api(value = "业务统计相关接口", tags= {"业务统计相关接口"})
@RestController
@RequestMapping("/stats/biz/summary")
public class StatsBizSummaryController {
    @Resource
    private StatsBizSummaryService statsBizSummaryService;

    @PostMapping
    public Result<StatsBizSummary> add(@RequestBody StatsBizSummary statsBizSummary) {
        statsBizSummaryService.save(statsBizSummary);
        return ResultGenerator.genSuccessResult(statsBizSummary);
    }
    
	@PostMapping("/batch")
	public Result<StatsUserLogin> batchAdd(@RequestParam int count) {
		statsBizSummaryService.batchAdd(count);
		return ResultGenerator.genSuccessResult();
	}
    
    @DeleteMapping("/{id}")
    public Result<StatsBizSummary> delete(@PathVariable String id) {
        statsBizSummaryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<StatsBizSummary> update(@RequestBody StatsBizSummary statsBizSummary) {
        statsBizSummaryService.update(statsBizSummary);
        return ResultGenerator.genSuccessResult(statsBizSummary);
    }

    @GetMapping("/{id}")
    public Result<StatsBizSummary> detail(@PathVariable String id) {
        StatsBizSummary statsBizSummary = statsBizSummaryService.findById(id);
        return ResultGenerator.genSuccessResult(statsBizSummary);
    }

    @GetMapping
    public Result<PageInfo<StatsBizSummary>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<StatsBizSummary> list = statsBizSummaryService.findAll();
        PageInfo<StatsBizSummary> pageInfo = new PageInfo<StatsBizSummary>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    /**
     * 业务信息统计
     * @param page
     * @param size
     * @param beginDate
     * @param endDate
     * @param orgId
     * @return
     */
    @ApiOperation(value = "获取业务统计信息，包括播单次数、喊话次数等，可以按日期和区域过滤")
    @GetMapping("/summary")
	public Result<PageInfo<StatsBizSummaryDto>> audienceSummary(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value = "开始日期") @RequestParam(defaultValue = "") String beginDate,
			@ApiParam(value = "结束日期") @RequestParam(defaultValue = "") String endDate,
			@ApiParam(value = "区域ID") @RequestParam(defaultValue = "") String orgId) {
    	beginDate = beginDate.trim();
		endDate = endDate.trim();
		orgId = orgId.trim();

		List<StatsBizSummaryDto> list = statsBizSummaryService.findByDayBizStatus(beginDate,endDate, orgId);

		PageHelper.startPage(page, size);
		PageInfo<StatsBizSummaryDto> pageInfo = new PageInfo<StatsBizSummaryDto>(list);

		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
