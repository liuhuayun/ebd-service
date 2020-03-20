package cn.comtom.cbs.controller;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.model.Org;
import cn.comtom.cbs.model.OrgPeers;
import cn.comtom.cbs.service.OrgPeersService;
import io.swagger.annotations.ApiParam;
import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrgPeers相关API。Created on 2018-12-29。
 * 
 * @author William
 */
@RestController
@RequestMapping("/org/peers")
public class OrgPeersController {
    @Resource
    private OrgPeersService orgPeersService;

    @PostMapping
    public Result<OrgPeers> add(@RequestBody OrgPeers orgPeers) {
        orgPeersService.save(orgPeers);
        return ResultGenerator.genSuccessResult(orgPeers);
    }
    
    @DeleteMapping("/{id}")
    public Result<OrgPeers> delete(@PathVariable String id) {
        orgPeersService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result<OrgPeers> update(@RequestBody OrgPeers orgPeers) {
        orgPeersService.update(orgPeers);
        return ResultGenerator.genSuccessResult(orgPeers);
    }

    @GetMapping("/{id}")
    public Result<OrgPeers> detail(@PathVariable String id) {
        OrgPeers orgPeers = orgPeersService.findById(id);
        return ResultGenerator.genSuccessResult(orgPeers);
    }

    @GetMapping
    public Result<PageInfo<OrgPeers>> list(@RequestParam(defaultValue = "0") Integer page, 
    		@RequestParam(defaultValue = "0") Integer size,
    		@ApiParam(value = "父区域ID") @RequestParam(defaultValue = "") String orgId) {
    	
    	orgId = orgId.trim();

		Condition condition = new Condition(Org.class);
		condition.createCriteria().andCondition("1=1");
		if (!orgId.isEmpty()) {
			orgId = "'" + orgId + "'";
			condition.and(condition.createCriteria().andCondition("parent_id=" + orgId));
		}
        PageHelper.startPage(page, size);
        List<OrgPeers> list = orgPeersService.findByCondition(condition);
        PageInfo<OrgPeers> pageInfo = new PageInfo<OrgPeers>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
