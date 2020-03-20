package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.Device;
import cn.comtom.cbs.service.DeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Device相关API。Created on 2019-06-19。
 * 
 * @author William
 */
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Resource
    private DeviceService deviceService;

    @PostMapping
    public Result<Device> add(@RequestBody Device device) {
        deviceService.save(device);
        return ResultGenerator.genSuccessResult(device);
    }
    
    @DeleteMapping("/{id}")
    public Result<Device> delete(@PathVariable String id) {
        deviceService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<Device> update(@RequestBody Device device) {
        deviceService.update(device);
        return ResultGenerator.genSuccessResult(device);
    }

    @GetMapping("/{id}")
    public Result<Device> detail(@PathVariable String id) {
        Device device = deviceService.findById(id);
        return ResultGenerator.genSuccessResult(device);
    }

    @GetMapping
    public Result<PageInfo<Device>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Device> list = deviceService.findAll();
        PageInfo<Device> pageInfo = new PageInfo<Device>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
