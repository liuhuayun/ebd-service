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
import cn.comtom.cbs.model.OperatorLog;
import cn.comtom.cbs.service.OperatorLogService;

/**
 * OperatorLog相关API。Created on 2018-12-28。
 * 
 * @author William
 */
@RestController
@RequestMapping("/operator/log")
public class OperatorLogController {
    @Resource
    private OperatorLogService operatorLogService;

    @PostMapping
    public Result<OperatorLog> add(@RequestBody OperatorLog operatorLog) {
        operatorLogService.save(operatorLog);
        return ResultGenerator.genSuccessResult(operatorLog);
    }
    
    @DeleteMapping("/{id}")
    public Result<OperatorLog> delete(@PathVariable String id) {
        operatorLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<OperatorLog> update(@RequestBody OperatorLog operatorLog) {
        operatorLogService.update(operatorLog);
        return ResultGenerator.genSuccessResult(operatorLog);
    }

    @GetMapping("/{id}")
    public Result<OperatorLog> detail(@PathVariable String id) {
        OperatorLog operatorLog = operatorLogService.findById(id);
        return ResultGenerator.genSuccessResult(operatorLog);
    }

    @GetMapping
    public Result<PageInfo<OperatorLog>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OperatorLog> list = operatorLogService.findAll();
        PageInfo<OperatorLog> pageInfo = new PageInfo<OperatorLog>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
}
