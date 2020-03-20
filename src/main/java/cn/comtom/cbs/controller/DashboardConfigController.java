package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.DashboardConfig;
import cn.comtom.cbs.model.Org;
import cn.comtom.cbs.service.DashboardConfigService;
import io.swagger.annotations.ApiParam;
import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * DashboardConfig相关API。Created on 2019-01-02。
 * 
 * @author William
 */
@RestController
@RequestMapping("/dashboard/config")
public class DashboardConfigController {
    @Resource
    private DashboardConfigService dashboardConfigService;

    @PostMapping
    public Result<DashboardConfig> add(@RequestBody DashboardConfig dashboardConfig) {
        dashboardConfigService.save(dashboardConfig);
        return ResultGenerator.genSuccessResult(dashboardConfig);
    }
    
    @DeleteMapping("/{id}")
    public Result<DashboardConfig> delete(@PathVariable int id) {
        dashboardConfigService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<DashboardConfig> update(@RequestBody DashboardConfig dashboardConfig) {
        dashboardConfigService.update(dashboardConfig);
        return ResultGenerator.genSuccessResult(dashboardConfig);
    }

    @GetMapping("/{id}")
    public Result<DashboardConfig> detail(@PathVariable int id) {
        DashboardConfig dashboardConfig = dashboardConfigService.findById(id);
        return ResultGenerator.genSuccessResult(dashboardConfig);
    }

    @GetMapping
    public Result<PageInfo<DashboardConfig>> list(@RequestParam(defaultValue = "0") Integer page, 
    		@RequestParam(defaultValue = "0") Integer size,
    		@ApiParam(value = "dashboard ID") @RequestParam(defaultValue = "") String dashboardId) {
    	dashboardId = dashboardId.trim();
    	Condition condition = new Condition(Org.class);
		condition.createCriteria().andCondition("1=1");
		if (!dashboardId.isEmpty()) {
			condition.and(condition.createCriteria().andCondition("dashboard_id='" + dashboardId + "'"));
		}
        PageHelper.startPage(page, size);
        List<DashboardConfig> list = dashboardConfigService.findByCondition(condition);
        PageInfo<DashboardConfig> pageInfo = new PageInfo<DashboardConfig>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
