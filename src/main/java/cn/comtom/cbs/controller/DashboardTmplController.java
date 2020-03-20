package cn.comtom.cbs.controller;

import java.util.Date;
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
import cn.comtom.cbs.dto.DashboardTmplDto;
import cn.comtom.cbs.model.DashboardTmpl;
import cn.comtom.cbs.model.DashboardTmplDetail;
import cn.comtom.cbs.model.Org;
import cn.comtom.cbs.service.DashboardTmplDetailService;
import cn.comtom.cbs.service.DashboardTmplService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import tk.mybatis.mapper.entity.Condition;

/**
 * DashboardTmpl相关API。Created on 2019-01-02。
 * 
 * @author William
 */
@Api(value = "Dashboard模板相关接口", tags= {"Dashboard模板相关接口"})
@RestController
@RequestMapping("/dashboard/tmpl")
public class DashboardTmplController {
    @Resource
    private DashboardTmplService dashboardTmplService;

    @Resource
    private DashboardTmplDetailService dashboardTmplDetailService;
    
    @PostMapping
    public Result<DashboardTmpl> add(@RequestBody DashboardTmpl dashboardTmpl) {
        dashboardTmplService.save(dashboardTmpl);
        return ResultGenerator.genSuccessResult(dashboardTmpl);
    }

    @PutMapping
    public Result<DashboardTmpl> update(@RequestBody DashboardTmpl dashboardTmpl) {
        dashboardTmplService.update(dashboardTmpl);
        return ResultGenerator.genSuccessResult(dashboardTmpl);
    }

    @GetMapping("/{id}")
    public Result<DashboardTmpl> detail(@PathVariable int id) {
        DashboardTmpl dashboardTmpl = dashboardTmplService.findById(id);
        return ResultGenerator.genSuccessResult(dashboardTmpl);
    }

    @GetMapping
    public Result<PageInfo<DashboardTmpl>> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
    		@ApiParam(value = "块的个数") @RequestParam(defaultValue = "") String blockNum) {
    	blockNum = blockNum.trim();

		Condition condition = new Condition(Org.class);
		condition.createCriteria().andCondition("1=1");
		if (!blockNum.isEmpty()) {
			blockNum = "'" + blockNum + "'";
			condition.and(condition.createCriteria().andCondition("block_num=" + blockNum));
		}
        PageHelper.startPage(page, size);
        List<DashboardTmpl> list = dashboardTmplService.findByCondition(condition);
        PageInfo<DashboardTmpl> pageInfo = new PageInfo<DashboardTmpl>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
	@ApiOperation(value = "添加新的Dashboard模板，包括模板信息和每一小块的配置")
	@PostMapping("/new")
	public Result<DashboardTmplDto> newTmpl(@RequestBody DashboardTmplDto dashboardTmplInfo) {
		DashboardTmpl dashboardTmpl = dashboardTmplInfo.getTmpl();
		dashboardTmpl.setSearchPosition(1);
		Date now = new Date();
		dashboardTmpl.setCreateTime(now);
		dashboardTmpl.setUpdateTime(now);
		dashboardTmplService.insert(dashboardTmpl);

		int tmplId = dashboardTmpl.getId();
		List<DashboardTmplDetail> dashboardTmplDetails = dashboardTmplInfo.getTmplDetails();
		for (DashboardTmplDetail d : dashboardTmplDetails) {
			d.setTmplId(tmplId);
			d.setCreateTime(now);
			d.setUpdateTime(now);
		}
		dashboardTmplDetailService.batchAdd(dashboardTmplDetails);
		return ResultGenerator.genSuccessResult(dashboardTmplInfo);
	}
	@ApiOperation(value = "删除Dashboard模板信息及块的配置信息")
	@DeleteMapping("/{id}")
	public Result<DashboardTmpl> delete(@PathVariable int id) {
		Condition condition = new Condition(DashboardTmplDetail.class);
		condition.createCriteria().andCondition("tmpl_id=" + id);
		List<DashboardTmplDetail> list = dashboardTmplDetailService.findByCondition(condition);
		for (DashboardTmplDetail d : list) {
			dashboardTmplDetailService.deleteById(d.getId());
		}
		dashboardTmplService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@ApiOperation(value = "更新Dashboard模板，包括模板信息和每一小块的配置")
	@PutMapping("/update")
	public Result<DashboardTmpl> updateTmplAndDetails(@RequestBody DashboardTmplDto dashboardTmplInfo) {
		DashboardTmpl dashboardTmpl = dashboardTmplInfo.getTmpl();

		int tmplId = dashboardTmpl.getId();

		// 先删除原来的小块信息
		Condition condition = new Condition(DashboardTmplDetail.class);
		condition.createCriteria().andCondition("tmpl_id=" + tmplId);
		List<DashboardTmplDetail> list = dashboardTmplDetailService.findByCondition(condition);
		for (DashboardTmplDetail d : list) {
			dashboardTmplDetailService.deleteById(d.getId());
		}
		// 重新插入新的小块信息
		List<DashboardTmplDetail> dashboardTmplDetails = dashboardTmplInfo.getTmplDetails();
		Date now = new Date();
		for (DashboardTmplDetail d : dashboardTmplDetails) {
			d.setTmplId(tmplId);
			d.setCreateTime(now);
			d.setUpdateTime(now);
		}
		dashboardTmplDetailService.batchAdd(dashboardTmplDetails);

		dashboardTmpl.setUpdateTime(now);
		dashboardTmplService.update(dashboardTmpl);
		return ResultGenerator.genSuccessResult(dashboardTmpl);
	}

	@ApiOperation(value = "以列表形式返回tmplId='id'的配置项")
	@GetMapping("/search")
	public Result<DashboardTmplDto> getListByModule(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size, @RequestParam(defaultValue = "") String tmplId) {
		tmplId = tmplId.trim();
		if (tmplId.isEmpty()) {
			return ResultGenerator.genSuccessResult(null);
		}

		DashboardTmplDto dashboardTmplDto = new DashboardTmplDto();
		DashboardTmpl dashboardTmpl = dashboardTmplService.findById(Integer.parseInt(tmplId));
		dashboardTmplDto.setTmpl(dashboardTmpl);

		Condition condition = new Condition(DashboardTmplDetail.class);
		condition.createCriteria().andCondition("tmpl_id=" + tmplId);
		List<DashboardTmplDetail> list = dashboardTmplDetailService.findByCondition(condition);
		dashboardTmplDto.setTmplDetails(list);

		return ResultGenerator.genSuccessResult(dashboardTmplDto);
	}

}
