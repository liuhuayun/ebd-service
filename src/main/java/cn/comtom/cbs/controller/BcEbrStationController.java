package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.BcEbrStation;
import cn.comtom.cbs.service.BcEbrStationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BcEbrStation相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/bc/ebr/station")
public class BcEbrStationController {
    @Resource
    private BcEbrStationService bcEbrStationService;

    @PostMapping
    public Result<BcEbrStation> add(@RequestBody BcEbrStation bcEbrStation) {
        bcEbrStationService.save(bcEbrStation);
        return ResultGenerator.genSuccessResult(bcEbrStation);
    }
    
    @DeleteMapping("/{id}")
    public Result<BcEbrStation> delete(@PathVariable String id) {
        bcEbrStationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<BcEbrStation> update(@RequestBody BcEbrStation bcEbrStation) {
        bcEbrStationService.update(bcEbrStation);
        return ResultGenerator.genSuccessResult(bcEbrStation);
    }

    @GetMapping("/{id}")
    public Result<BcEbrStation> detail(@PathVariable String id) {
        BcEbrStation bcEbrStation = bcEbrStationService.findById(id);
        return ResultGenerator.genSuccessResult(bcEbrStation);
    }

    @GetMapping
    public Result<PageInfo<BcEbrStation>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BcEbrStation> list = bcEbrStationService.findAll();
        PageInfo<BcEbrStation> pageInfo = new PageInfo<BcEbrStation>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
