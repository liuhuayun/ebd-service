package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.SysRegionArea;
import cn.comtom.cbs.service.SysRegionAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * SysRegionArea相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/sys/region/area")
public class SysRegionAreaController {
    @Resource
    private SysRegionAreaService sysRegionAreaService;

    @PostMapping
    public Result<SysRegionArea> add(@RequestBody SysRegionArea sysRegionArea) {
        sysRegionAreaService.save(sysRegionArea);
        return ResultGenerator.genSuccessResult(sysRegionArea);
    }
    
    @DeleteMapping("/{id}")
    public Result<SysRegionArea> delete(@PathVariable String id) {
        sysRegionAreaService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<SysRegionArea> update(@RequestBody SysRegionArea sysRegionArea) {
        sysRegionAreaService.update(sysRegionArea);
        return ResultGenerator.genSuccessResult(sysRegionArea);
    }

    @GetMapping("/{id}")
    public Result<SysRegionArea> detail(@PathVariable String id) {
        SysRegionArea sysRegionArea = sysRegionAreaService.findById(id);
        return ResultGenerator.genSuccessResult(sysRegionArea);
    }

    @GetMapping
    public Result<PageInfo<SysRegionArea>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysRegionArea> list = sysRegionAreaService.findAll();
        PageInfo<SysRegionArea> pageInfo = new PageInfo<SysRegionArea>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
