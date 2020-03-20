package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.DashboardTmplDetail;
import cn.comtom.cbs.service.DashboardTmplDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * DashboardTmplDetail相关API。Created on 2019-01-02。
 * 
 * @author William
 */
@RestController
@RequestMapping("/dashboard/tmpl/detail")
public class DashboardTmplDetailController {
    @Resource
    private DashboardTmplDetailService dashboardTmplDetailService;

    @PostMapping
    public Result<DashboardTmplDetail> add(@RequestBody DashboardTmplDetail dashboardTmplDetail) {
        dashboardTmplDetailService.save(dashboardTmplDetail);
        return ResultGenerator.genSuccessResult(dashboardTmplDetail);
    }
    
    @DeleteMapping("/{id}")
    public Result<DashboardTmplDetail> delete(@PathVariable int id) {
        dashboardTmplDetailService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<DashboardTmplDetail> update(@RequestBody DashboardTmplDetail dashboardTmplDetail) {
        dashboardTmplDetailService.update(dashboardTmplDetail);
        return ResultGenerator.genSuccessResult(dashboardTmplDetail);
    }

    @GetMapping("/{id}")
    public Result<DashboardTmplDetail> detail(@PathVariable int id) {
        DashboardTmplDetail dashboardTmplDetail = dashboardTmplDetailService.findById(id);
        return ResultGenerator.genSuccessResult(dashboardTmplDetail);
    }

    @GetMapping
    public Result<PageInfo<DashboardTmplDetail>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DashboardTmplDetail> list = dashboardTmplDetailService.findAll();
        PageInfo<DashboardTmplDetail> pageInfo = new PageInfo<DashboardTmplDetail>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
