package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.BcEbmBrdRecord;
import cn.comtom.cbs.service.BcEbmBrdRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * BcEbmBrdRecord相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/bc/ebm/brd/record")
public class BcEbmBrdRecordController {
    @Resource
    private BcEbmBrdRecordService bcEbmBrdRecordService;

    @PostMapping
    public Result<BcEbmBrdRecord> add(@RequestBody BcEbmBrdRecord bcEbmBrdRecord) {
        bcEbmBrdRecordService.save(bcEbmBrdRecord);
        return ResultGenerator.genSuccessResult(bcEbmBrdRecord);
    }
    
    @DeleteMapping("/{id}")
    public Result<BcEbmBrdRecord> delete(@PathVariable String id) {
        bcEbmBrdRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<BcEbmBrdRecord> update(@RequestBody BcEbmBrdRecord bcEbmBrdRecord) {
        bcEbmBrdRecordService.update(bcEbmBrdRecord);
        return ResultGenerator.genSuccessResult(bcEbmBrdRecord);
    }

    @GetMapping("/{id}")
    public Result<BcEbmBrdRecord> detail(@PathVariable String id) {
        BcEbmBrdRecord bcEbmBrdRecord = bcEbmBrdRecordService.findById(id);
        return ResultGenerator.genSuccessResult(bcEbmBrdRecord);
    }

    @GetMapping
    public Result<PageInfo<BcEbmBrdRecord>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BcEbmBrdRecord> list = bcEbmBrdRecordService.findAll();
        PageInfo<BcEbmBrdRecord> pageInfo = new PageInfo<BcEbmBrdRecord>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
