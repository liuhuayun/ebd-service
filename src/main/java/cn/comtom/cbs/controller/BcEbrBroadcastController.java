package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.BcEbrBroadcast;
import cn.comtom.cbs.service.BcEbrBroadcastService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BcEbrBroadcast相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/bc/ebr/broadcast")
public class BcEbrBroadcastController {
    @Resource
    private BcEbrBroadcastService bcEbrBroadcastService;

    @PostMapping
    public Result<BcEbrBroadcast> add(@RequestBody BcEbrBroadcast bcEbrBroadcast) {
        bcEbrBroadcastService.save(bcEbrBroadcast);
        return ResultGenerator.genSuccessResult(bcEbrBroadcast);
    }
    
    @DeleteMapping("/{id}")
    public Result<BcEbrBroadcast> delete(@PathVariable String id) {
        bcEbrBroadcastService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<BcEbrBroadcast> update(@RequestBody BcEbrBroadcast bcEbrBroadcast) {
        bcEbrBroadcastService.update(bcEbrBroadcast);
        return ResultGenerator.genSuccessResult(bcEbrBroadcast);
    }

    @GetMapping("/{id}")
    public Result<BcEbrBroadcast> detail(@PathVariable String id) {
        BcEbrBroadcast bcEbrBroadcast = bcEbrBroadcastService.findById(id);
        return ResultGenerator.genSuccessResult(bcEbrBroadcast);
    }

    @GetMapping
    public Result<PageInfo<BcEbrBroadcast>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BcEbrBroadcast> list = bcEbrBroadcastService.findAll();
        PageInfo<BcEbrBroadcast> pageInfo = new PageInfo<BcEbrBroadcast>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
