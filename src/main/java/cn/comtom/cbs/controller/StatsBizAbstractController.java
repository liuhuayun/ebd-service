package cn.comtom.cbs.controller;

import java.io.IOException;
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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.dto.StatsBizAbstractDto;
import cn.comtom.cbs.model.StatsBizAbstract;
import cn.comtom.cbs.service.StatsBizAbstractService;
import cn.comtom.cbs.service.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * StatsBizAbstract相关API。Created on 2018-12-31。
 * 
 * @author William
 */
@Api(value = "业务动态相关接口", tags = { "业务动态相关接口" })
@RestController
@RequestMapping("/stats/biz/abstract")
public class StatsBizAbstractController {
	@Resource
	private StatsBizAbstractService statsBizAbstractService;

	@PostMapping
	public Result<StatsBizAbstract> add(@RequestBody StatsBizAbstract statsBizAbstract) {
		statsBizAbstractService.save(statsBizAbstract);
		try {
			WebSocket.sendJson("news", JSON.toJSONString(statsBizAbstract));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultGenerator.genSuccessResult(statsBizAbstract);
	}

	@DeleteMapping("/{id}")
	public Result<StatsBizAbstract> delete(@PathVariable String id) {
		statsBizAbstractService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result<StatsBizAbstract> update(@RequestBody StatsBizAbstract statsBizAbstract) {
		statsBizAbstractService.update(statsBizAbstract);
		return ResultGenerator.genSuccessResult(statsBizAbstract);
	}

	@GetMapping("/{id}")
	public Result<StatsBizAbstract> detail(@PathVariable String id) {
		StatsBizAbstract statsBizAbstract = statsBizAbstractService.findById(id);
		return ResultGenerator.genSuccessResult(statsBizAbstract);
	}

	@GetMapping
	public Result<PageInfo<StatsBizAbstract>> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<StatsBizAbstract> list = statsBizAbstractService.findAll();
		PageInfo<StatsBizAbstract> pageInfo = new PageInfo<StatsBizAbstract>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	/**
	 * 查找最新的业务动态
	 * 
	 * @param page
	 * @param size
	 * @param date
	 * @param orgId
	 * @return
	 */
	@ApiOperation(value = "获取最新的业务动态，可以按日期和区域过滤")
	@GetMapping("/latest")
	public Result<PageInfo<StatsBizAbstractDto>> latestDynamicInfo(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size,
			@ApiParam(value = "查询日期") @RequestParam(defaultValue = "") String date,
			@ApiParam(value = "区域ID") @RequestParam(defaultValue = "") String orgId) {
		date = date.trim();
		orgId = orgId.trim();

		PageHelper.startPage(page, size);
		List<StatsBizAbstractDto> list = statsBizAbstractService.findByDay(date, orgId);
		PageInfo<StatsBizAbstractDto> pageInfo = new PageInfo<StatsBizAbstractDto>(list);

		return ResultGenerator.genSuccessResult(pageInfo);
	}

}
