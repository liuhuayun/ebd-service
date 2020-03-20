package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.DevStatusLog;
import cn.comtom.cbs.service.DevStatusLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * DevStatusLog相关API。Created on 2018-12-28。
 * 
 * @author William
 */
@RestController
@RequestMapping("/dev/status/log")
public class DevStatusLogController {
    @Resource
    private DevStatusLogService devStatusLogService;

    @PostMapping
    public Result<DevStatusLog> add(@RequestBody DevStatusLog devStatusLog) {
        devStatusLogService.save(devStatusLog);
        return ResultGenerator.genSuccessResult(devStatusLog);
    }
    
    @DeleteMapping("/{id}")
    public Result<DevStatusLog> delete(@PathVariable String id) {
        devStatusLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<DevStatusLog> update(@RequestBody DevStatusLog devStatusLog) {
        devStatusLogService.update(devStatusLog);
        return ResultGenerator.genSuccessResult(devStatusLog);
    }

    @GetMapping("/{id}")
    public Result<DevStatusLog> detail(@PathVariable String id) {
        DevStatusLog devStatusLog = devStatusLogService.findById(id);
        return ResultGenerator.genSuccessResult(devStatusLog);
    }

    @GetMapping
    public Result<PageInfo<DevStatusLog>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DevStatusLog> list = devStatusLogService.findAll();
        PageInfo<DevStatusLog> pageInfo = new PageInfo<DevStatusLog>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
