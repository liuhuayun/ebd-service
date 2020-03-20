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
import cn.comtom.cbs.dto.ebm.EbmInfoAllDto;
import cn.comtom.cbs.model.BcEbm;
import cn.comtom.cbs.service.BcEbmService;
import io.swagger.annotations.ApiOperation;

/**
 * BcEbm相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/bc/ebm")
public class BcEbmController {
    @Resource
    private BcEbmService bcEbmService;

    @PostMapping
    public Result<BcEbm> add(@RequestBody BcEbm bcEbm) {
        bcEbmService.save(bcEbm);
        return ResultGenerator.genSuccessResult(bcEbm);
    }
    
    @DeleteMapping("/{id}")
    public Result<BcEbm> delete(@PathVariable String id) {
        bcEbmService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<BcEbm> update(@RequestBody BcEbm bcEbm) {
        bcEbmService.update(bcEbm);
        return ResultGenerator.genSuccessResult(bcEbm);
    }

    @GetMapping("/{id}")
    public Result<BcEbm> detail(@PathVariable String id) {
        BcEbm bcEbm = bcEbmService.findById(id);
        return ResultGenerator.genSuccessResult(bcEbm);
    }
    
    @GetMapping
    public Result<PageInfo<BcEbm>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BcEbm> list = bcEbmService.findAll();
        PageInfo<BcEbm> pageInfo = new PageInfo<BcEbm>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    @GetMapping("/queryListLimit")
    @ApiOperation(value = "查询预警接入信息", notes = "查询预警接入信息")
    public Result<List<BcEbm>> queryListLimit(@RequestParam(defaultValue = "10") Integer limit) {
		try {
			PageHelper.startPage(0, limit);
			List<BcEbm> datas = bcEbmService.queryListLimit(limit);
			return ResultGenerator.genSuccessResult(datas);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("内部服务错误："+e.getMessage());
		}
    	
    }
    
    @GetMapping("/queryEbmInfoAll")
    @ApiOperation(value = "查询预警接入信息及其关联信息记录", notes = "查询预警接入信息及其关联信息记录")
    public Result<EbmInfoAllDto> getEbmInfoAll(@RequestParam(name = "ebmId") String ebmId){
    	try {
			EbmInfoAllDto result = bcEbmService.findEbmInfoAll(ebmId);
			return ResultGenerator.genSuccessResult(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("内部服务错误："+e.getMessage());
		}
    }
}
