package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.BcEbrPlatform;
import cn.comtom.cbs.service.BcEbrPlatformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BcEbrPlatform相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/bc/ebr/platform")
public class BcEbrPlatformController {
    @Resource
    private BcEbrPlatformService bcEbrPlatformService;

    @PostMapping
    public Result<BcEbrPlatform> add(@RequestBody BcEbrPlatform bcEbrPlatform) {
        bcEbrPlatformService.save(bcEbrPlatform);
        return ResultGenerator.genSuccessResult(bcEbrPlatform);
    }
    
    @DeleteMapping("/{id}")
    public Result<BcEbrPlatform> delete(@PathVariable String id) {
        bcEbrPlatformService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<BcEbrPlatform> update(@RequestBody BcEbrPlatform bcEbrPlatform) {
        bcEbrPlatformService.update(bcEbrPlatform);
        return ResultGenerator.genSuccessResult(bcEbrPlatform);
    }

    @GetMapping("/{id}")
    public Result<BcEbrPlatform> detail(@PathVariable String id) {
        BcEbrPlatform bcEbrPlatform = bcEbrPlatformService.findById(id);
        return ResultGenerator.genSuccessResult(bcEbrPlatform);
    }

    @GetMapping
    public Result<PageInfo<BcEbrPlatform>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BcEbrPlatform> list = bcEbrPlatformService.findAll();
        PageInfo<BcEbrPlatform> pageInfo = new PageInfo<BcEbrPlatform>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
