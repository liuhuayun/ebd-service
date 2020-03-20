package cn.comtom.cbs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.dto.ChartDataDto;
import cn.comtom.cbs.dto.DashboardDto;
import cn.comtom.cbs.model.ChartConfig;
import cn.comtom.cbs.model.Dashboard;
import cn.comtom.cbs.model.DashboardConfig;
import cn.comtom.cbs.model.DashboardTmpl;
import cn.comtom.cbs.model.DashboardTmplDetail;
import cn.comtom.cbs.service.ChartConfigService;
import cn.comtom.cbs.service.DashboardConfigService;
import cn.comtom.cbs.service.DashboardService;
import cn.comtom.cbs.service.DashboardTmplDetailService;
import cn.comtom.cbs.service.DashboardTmplService;
import cn.comtom.cbs.service.DevService;
import cn.comtom.cbs.service.StatsBizSummaryService;
import cn.comtom.cbs.service.StatsUserLoginService;
import cn.comtom.cbs.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import tk.mybatis.mapper.entity.Condition;

/**
 * Dashboard相关API。Created on 2019-01-02。
 * 
 * @author William
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	// private static final String API_SUCCESS_CODE = "200";
	private static final String API_SUCCESS_CODE = "CBS-000";

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Resource
	private DashboardService dashboardService;
	@Resource
	private DashboardConfigService dashboardConfigService;
	@Resource
	private ChartConfigService chartConfigService;
	@Resource
	private DashboardTmplService dashboardTmplService;

	@Resource
	private DashboardTmplDetailService dashboardTmplDetailService;

	@Resource
	private StatsUserLoginService statsUserLoginService;
	@Resource
	private DevService devService;
	@Resource
	private UserService userService;
	@Resource
	private StatsBizSummaryService statsBizSummaryService;

	// 文件访问路径
	@Value("${business.server.baseUrl}")
	private String baseUrl;

	@PostMapping
	public Result<Dashboard> add(@RequestBody Dashboard dashboard) {
		dashboardService.save(dashboard);
		return ResultGenerator.genSuccessResult(dashboard);
	}

	@DeleteMapping("/{id}")
	public Result<Dashboard> delete(@PathVariable int id) {
		dashboardService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result<Dashboard> update(@RequestBody Dashboard dashboard) {
		dashboardService.update(dashboard);
		return ResultGenerator.genSuccessResult(dashboard);
	}

	@GetMapping("/{id}")
	public Result<Dashboard> detail(@PathVariable int id) {
		Dashboard dashboard = dashboardService.findById(id);
		return ResultGenerator.genSuccessResult(dashboard);
	}

	@GetMapping
	public Result<PageInfo<Dashboard>> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<Dashboard> list = dashboardService.findAll();
		PageInfo<Dashboard> pageInfo = new PageInfo<Dashboard>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@ApiOperation(value = "返回dashboard需要的所有信息")
	@GetMapping("/detail")
	public Result<DashboardDto> getListByModule(HttpServletRequest request,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
			@RequestParam(defaultValue = "") String id, @RequestParam(defaultValue = "") String moduleName,
			@RequestParam(defaultValue = "") String orgId,
			@ApiParam(value = "开始日期") @RequestParam(defaultValue = "") String beginDate,
			@ApiParam(value = "结束日期") @RequestParam(defaultValue = "") String endDate) {
		moduleName = moduleName.trim();
		id = id.trim();
		orgId = orgId.trim();
		beginDate = beginDate.trim();
		endDate = endDate.trim();

		// 获取dashboard信息
		Dashboard dashboard = null;
		if (!id.isEmpty()) {
			int dashboardId = Integer.parseInt(id);
			dashboard = dashboardService.findById(dashboardId);
		} else if (!moduleName.isEmpty()) {
			System.out.println("moduleName = " + moduleName);
			dashboard = dashboardService.findBy("moduleName", moduleName);
		} else {
			// error
			return ResultGenerator.genFailResult("id或moduleName至少传一个！");
		}
		if (dashboard == null) {
			return ResultGenerator.genFailResult("没有找到对应的Dashboard!");
		}
		// 获取dashboard详细配置信息
		List<DashboardConfig> dashboardConfigs = dashboardConfigService
				.findByDashboardIdAndAscByBlockIndex(dashboard.getId());

		// 先删除没有对应图表的config
		int deleted = 0;
		for (DashboardConfig config : dashboardConfigs) {
			ChartConfig chartConfig = chartConfigService.findById(config.getConfigId());
			if (chartConfig == null) {
				dashboardConfigService.deleteById(config.getId());
				deleted = 1;
			}
		}
		// 重新获取dashboard configs
		if (deleted == 1) {
			dashboardConfigs = dashboardConfigService.findByDashboardIdAndAscByBlockIndex(dashboard.getId());
		}
		// 获取请求头中的access_token
		// String headVal = request.getHeader("Authorization");
		// String headVal = request.getHeader("ktd");
		String headVal = "";

		// System.out.println("Authorization: " + headVal);

		// 获取每个图表需要的数据
		List<ChartDataDto> charts = new ArrayList<>();
		for (DashboardConfig config : dashboardConfigs) {
			ChartDataDto dataDto = new ChartDataDto();

			ChartConfig chartConfig = chartConfigService.findById(config.getConfigId());
			List<?> list = this.getListByApi(chartConfig, orgId, beginDate, endDate, headVal);

			dataDto.setChartData(list);
			dataDto.setChartConfig(chartConfig);

			charts.add(dataDto);
		}
		// 获取dashboard模板信息
		int tmplId = dashboard.getTmplId();
		DashboardTmpl tmpl = dashboardTmplService.findById(tmplId);

		Condition condition = new Condition(DashboardTmplDetail.class);
		condition.createCriteria().andCondition("tmpl_id=" + tmplId);
		List<DashboardTmplDetail> tmplDetails = dashboardTmplDetailService.findByCondition(condition);

		DashboardDto dashboardDto = new DashboardDto();
		dashboardDto.setDashboard(dashboard);
		dashboardDto.setDashboardConfig(dashboardConfigs);
		dashboardDto.setTmpl(tmpl);
		dashboardDto.setTmplDetails(tmplDetails);
		dashboardDto.setCharts(charts);

		return ResultGenerator.genSuccessResult(dashboardDto);
	}

	private List<?> getList(ChartConfig chartConfig, String orgId) {
		String chartType = chartConfig.getChartType();
		List<?> list = new ArrayList<>();
		if (chartType.equals("Rolling") || chartType.equals("SafeInfo") || chartType.equals("Bar")) {
			// 不需要在这里获取数据。直接在前端组件中获取数据
		} else {
			if (chartConfig.getModuleName().equals("user_login")) {
				String sumType = chartConfig.getDimension();
				list = statsUserLoginService.summary(sumType, orgId, "", "");
			} else if (chartConfig.getModuleName().equals("dev")) {
				if (chartConfig.getApiName().equals("summary")) {
					String sumType = chartConfig.getDimension();
					list = devService.summary(sumType, orgId);
				} else if (chartConfig.getApiName().equals("total")) {
					String sumType = chartConfig.getDimension();
					list = devService.total(sumType, orgId);
				} else {
					Condition condition = devService.createGetDevListCondition("", "", orgId);
					list = devService.findByCondition(condition);
				}
			} else if (chartConfig.getModuleName().equals("user")) {
				if (chartConfig.getApiName().equals("summary")) {
					String sumType = chartConfig.getDimension();
					list = userService.summary(sumType, orgId);
				} else if (chartConfig.getApiName().equals("total")) {
					String sumType = chartConfig.getDimension();
					list = userService.total(sumType, orgId);
				}
			} else if (chartConfig.getModuleName().equals("statsBizSummary")) {
				list = statsBizSummaryService.findByDayBizStatus("", "", orgId);
			}
		}
		return list;
	}

	private List<?> getListByApi(ChartConfig chartConfig, String orgId, String beginDate, String endDate,
			String headVal) {
		String sumType = chartConfig.getDimension();
		String period = chartConfig.getPeriod();

		String searchConditions = "?sumType=" + sumType + "&period=" + period + "&orgId=" + orgId
				+ "&startTime=&endTime=&page=1&pageSize=10&beginDate=" + beginDate + "&endDate=" + endDate;
		String chartType = chartConfig.getChartType();
		String urlPath = "";
		@SuppressWarnings("rawtypes")
		List<Map> l = new ArrayList<Map>();
		if (chartType.equals("Rolling") || chartType.equals("SafeInfo") || chartType.equals("Bar") || chartType.equals("EbRolling") || chartType.equals("EbMap")) {
			// 不需要在这里获取数据。直接在前端组件中获取数据
			return l;
		} else {
			if (chartConfig.getModuleName().equals("user_login")) {
				urlPath = "/stats/user/activeUserSummary";
			} else if (chartConfig.getModuleName().equals("dev")) {
				if (chartConfig.getApiName().equals("summary")) {
					urlPath = "/stats/dev/summary";
				} else if (chartConfig.getApiName().equals("total")) {
					urlPath = "/dev/total";
				} else {
					urlPath = "/stats/dev";
					// searchConditions += "&startTime=" + beginDate + "&endTime=" + endDate +
					// "&page=1&pageSize=1000000";
				}
			} else if (chartConfig.getModuleName().equals("user")) {
				if (chartConfig.getApiName().equals("summary")) {
					urlPath = "/stats/user/increaseUserSummary";
				} 
			} else if (chartConfig.getModuleName().equals("statsBizSummary")) {
				urlPath = "/stats/biz/summary";
			}
		}

		// 通过接口调用得到需要的数据
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// headers.set("Authorization", headVal);// 设置Authorization token值
		// headers.set("token", headVal.substring(6));

		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);

		String url = baseUrl + urlPath + searchConditions;
		logger.info("url = {}", url);
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			logger.info(responseEntity.getBody().toString());
			JSONObject res = JSONObject.fromObject(responseEntity.getBody());

			String code = res.get("code").toString();
			if (!code.equalsIgnoreCase(API_SUCCESS_CODE)) {
				// error
				String message = "moduleName = " + chartConfig.getModuleName() + "; apiName = "
						+ chartConfig.getApiName();
				logger.info("获取图表数据失败: " + message);
			} else {
				if (chartConfig.getModuleName().equals("dev") && chartConfig.getApiName().equals("summary")) {
					// 这种情况下，data里面保存的是一个对象
					JSONObject d = JSONObject.fromObject(res.get("data"));
					Map m = JSON.parseObject(d.toString());
					l.add(m);
				} else {
					// 这种情况下，data里面保存的是一个对象列表
					JSONArray array = res.getJSONArray("data");

					for (int i = 0; i < array.size(); i++) {
						Object el = array.get(i);
						if (el instanceof JSONNull) {
							break;
						}
						JSONObject object = (JSONObject) array.get(i);
						@SuppressWarnings("rawtypes")
						Map m = JSON.parseObject(object.toString());
						// MapDto m = (MapDto) JSONObject.toBean(object, MapDto.class);
						l.add(m);
					}
				}

			}
		} catch (Exception e) {
			String message = "moduleName = " + chartConfig.getModuleName() + "; apiName = "
					+ chartConfig.getApiName();
			logger.error("获取图表数据失败: message = {}, url = {}", message, url);		

			e.printStackTrace();			
		}

		return l;
	}
}
