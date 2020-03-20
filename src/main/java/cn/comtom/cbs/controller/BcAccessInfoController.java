package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.BcAccessInfo;
import cn.comtom.cbs.service.BcAccessInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BcAccessInfo相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/bc/access/info")
public class BcAccessInfoController {
    @Resource
    private BcAccessInfoService bcAccessInfoService;

    @PostMapping
    public Result<BcAccessInfo> add(@RequestBody BcAccessInfo bcAccessInfo) {
        bcAccessInfoService.save(bcAccessInfo);
        return ResultGenerator.genSuccessResult(bcAccessInfo);
    }
    
    @DeleteMapping("/{id}")
    public Result<BcAccessInfo> delete(@PathVariable String id) {
        bcAccessInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<BcAccessInfo> update(@RequestBody BcAccessInfo bcAccessInfo) {
        bcAccessInfoService.update(bcAccessInfo);
        return ResultGenerator.genSuccessResult(bcAccessInfo);
    }

    @GetMapping("/{id}")
    public Result<BcAccessInfo> detail(@PathVariable String id) {
        BcAccessInfo bcAccessInfo = bcAccessInfoService.findById(id);
        return ResultGenerator.genSuccessResult(bcAccessInfo);
    }

    @GetMapping
    public Result<PageInfo<BcAccessInfo>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BcAccessInfo> list = bcAccessInfoService.findAll();
        PageInfo<BcAccessInfo> pageInfo = new PageInfo<BcAccessInfo>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
