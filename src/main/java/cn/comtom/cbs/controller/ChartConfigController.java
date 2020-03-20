package cn.comtom.cbs.controller;

import java.util.Date;
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
import cn.comtom.cbs.model.ChartConfig;
import cn.comtom.cbs.model.Org;
import cn.comtom.cbs.service.ChartConfigService;
import io.swagger.annotations.ApiParam;
import tk.mybatis.mapper.entity.Condition;

/**
 * ChartConfig相关API。Created on 2019-01-01。
 * 
 * @author William
 */
@RestController
@RequestMapping("/chart/config")
public class ChartConfigController {
    @Resource
    private ChartConfigService chartConfigService;

    @PostMapping
    public Result<ChartConfig> add(@RequestBody ChartConfig chartConfig) {
    	Date now = new Date();
    	chartConfig.setCreateTime(now);
    	chartConfig.setUpdateTime(now);
        chartConfigService.save(chartConfig);
        return ResultGenerator.genSuccessResult(chartConfig);
    }
    
    @DeleteMapping("/{id}")
    public Result<ChartConfig> delete(@PathVariable Integer id) {
        chartConfigService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<ChartConfig> update(@RequestBody ChartConfig chartConfig) {
     	Date now = new Date();
    	chartConfig.setUpdateTime(now);
        chartConfigService.update(chartConfig);
        return ResultGenerator.genSuccessResult(chartConfig);
    }

    @GetMapping("/{id}")
    public Result<ChartConfig> detail(@PathVariable Integer id) {
        ChartConfig chartConfig = chartConfigService.findById(id);
        return ResultGenerator.genSuccessResult(chartConfig);
    }

    @GetMapping
    public Result<PageInfo<ChartConfig>> list(@RequestParam(defaultValue = "0") Integer page, 
    		@RequestParam(defaultValue = "0") Integer size,
    		@ApiParam(value = "模块名") @RequestParam(defaultValue = "") String moduleName,
			@ApiParam(value = "图表名关键字") @RequestParam(defaultValue = "") String configName) {
    	moduleName = moduleName.trim();
		configName = configName.trim();

		Condition condition = new Condition(Org.class);
		condition.createCriteria().andCondition("1=1");
		if (!moduleName.isEmpty()) {
			condition.and(condition.createCriteria().andCondition("module_name like '%" + moduleName + "%'"));
		}
		if (!configName.isEmpty()) {
			condition.and(condition.createCriteria().andCondition("config_name like '%" + configName + "%'"));
		}
        PageHelper.startPage(page, size);
        List<ChartConfig> list = chartConfigService.findByCondition(condition);
        PageInfo<ChartConfig> pageInfo = new PageInfo<ChartConfig>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
