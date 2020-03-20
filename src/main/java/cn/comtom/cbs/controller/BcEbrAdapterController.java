package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.BcEbrAdapter;
import cn.comtom.cbs.service.BcEbrAdapterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BcEbrAdapter相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/bc/ebr/adapter")
public class BcEbrAdapterController {
    @Resource
    private BcEbrAdapterService bcEbrAdapterService;

    @PostMapping
    public Result<BcEbrAdapter> add(@RequestBody BcEbrAdapter bcEbrAdapter) {
        bcEbrAdapterService.save(bcEbrAdapter);
        return ResultGenerator.genSuccessResult(bcEbrAdapter);
    }
    
    @DeleteMapping("/{id}")
    public Result<BcEbrAdapter> delete(@PathVariable String id) {
        bcEbrAdapterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<BcEbrAdapter> update(@RequestBody BcEbrAdapter bcEbrAdapter) {
        bcEbrAdapterService.update(bcEbrAdapter);
        return ResultGenerator.genSuccessResult(bcEbrAdapter);
    }

    @GetMapping("/{id}")
    public Result<BcEbrAdapter> detail(@PathVariable String id) {
        BcEbrAdapter bcEbrAdapter = bcEbrAdapterService.findById(id);
        return ResultGenerator.genSuccessResult(bcEbrAdapter);
    }

    @GetMapping
    public Result<PageInfo<BcEbrAdapter>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BcEbrAdapter> list = bcEbrAdapterService.findAll();
        PageInfo<BcEbrAdapter> pageInfo = new PageInfo<BcEbrAdapter>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
