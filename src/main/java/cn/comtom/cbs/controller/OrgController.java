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
import cn.comtom.cbs.model.Org;
import cn.comtom.cbs.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import tk.mybatis.mapper.entity.Condition;

/**
 * Org相关API。Created on 2018-12-28。
 * 
 * @author William
 */
@Api(value = "区域相关接口", tags= {"区域相关接口"})
@RestController
@RequestMapping("/org")
public class OrgController {
	@Resource
	private OrgService orgService;

	@PostMapping
	public Result<Org> add(@RequestBody Org org) {
		orgService.save(org);
		return ResultGenerator.genSuccessResult(org);
	}

	@DeleteMapping("/{id}")
	public Result<Org> delete(@PathVariable String id) {
		orgService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result<Org> update(@RequestBody Org org) {
		orgService.update(org);
		return ResultGenerator.genSuccessResult(org);
	}

	@GetMapping("/{id}")
	public Result<Org> detail(@PathVariable String id) {
		Org org = orgService.findById(id);
		return ResultGenerator.genSuccessResult(org);
	}

	@ApiOperation(value = "获取区域列表")
	//@Cacheable(cacheNames="org_list", key="#parentId")
	@GetMapping
	public Result<PageInfo<Org>> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value = "上级区域ID") @RequestParam(defaultValue = "") String parentId,
			@ApiParam(value = "是否为根节点") @RequestParam(defaultValue = "") String rooted,
			@ApiParam(value = "区域名关键字") @RequestParam(defaultValue = "") String orgName) {
		parentId = parentId.trim();
		rooted = rooted.trim();
		orgName = orgName.trim();

		Condition condition = new Condition(Org.class);
		condition.createCriteria().andCondition("1=1");
		if (!parentId.isEmpty()) {
			parentId = "'" + parentId + "'";
			condition.and(condition.createCriteria().andCondition("parent_id=" + parentId));
			rooted = "";
		}
		if (!rooted.isEmpty()) {
			rooted = "'" + rooted + "'";
			condition.and(condition.createCriteria().andCondition("rooted=" + rooted));
		}
		if (!orgName.isEmpty()) {
			condition.and(condition.createCriteria().andCondition("org_name like '%" + orgName + "%'"));
		}
		
		PageHelper.startPage(page, size);
		List<Org> list = orgService.findByCondition(condition);
		
		PageInfo<Org> pageInfo = new PageInfo<Org>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
