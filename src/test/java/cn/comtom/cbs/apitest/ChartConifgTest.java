package cn.comtom.cbs.apitest;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import net.sf.json.JSONObject;

public class ChartConifgTest {
	private String url = ApiTestCommon.BASE_URL + "/chart/config";
	private int id=0;
	private String configName=null;
	private String moduleName=null;
	private String apiName=null;
	private String chartType=null;
	private String chartName=null;
	private String dimension=null;
	//private String dimName=null;
	private String perfIndex=null;
	private String indexName=null;
	
	@假如("^我要创建一个id为\"([^\"]*)\",图表配置名称为\"([^\"]*)\",模块名称为\"([^\"]*)\",接口名称为\"([^\"]*)\",图表类型为\"([^\"]*)\",图表名称为\"([^\"]*)\",纬度为\"([^\"]*)\",性能指标为\"([^\"]*)\",性能指标名称为\"([^\"]*)\"的图表配置信息$")
	public void 我要创建一个id为_图表配置名称为_模块名称为_接口名称为_图表类型为_图表名称为_纬度为_性能指标为_性能指标名称为_的图表配置信息(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.id=Integer.parseInt(arg1);
		this.configName=arg2;
		this.moduleName=arg3;
		this.apiName=arg4;
		this.chartType=arg5;
		this.chartName=arg6;
		this.dimension=arg7;
		this.perfIndex=arg8;
		this.indexName=arg9;
		
	}

	@当("^我发送创建新的图表配置信息请求时$")
	public void 我发送创建新的图表配置信息请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("configName", this.configName);
		map.put("moduleName", this.moduleName);
		map.put("apiName", this.apiName);
		map.put("chartType", this.chartType);
		map.put("chartName", this.chartName);
		map.put("dimension", this.dimension);
		map.put("perfIndex", this.perfIndex);
		map.put("indexName", this.indexName);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		  // System.out.println("创建请求body：" + jsonObject);
			String result = jsonObject.toString();
			// String u = ApiTestCommon.BASE_URL + "/operator/log";
			String myUrl = this.url;
			ApiTestCommon.res = ApiTestCommon.postJson(myUrl, result);
			System.out.println("res: " + ApiTestCommon.res);
	}

	@那么("^我应该成功创建新的图表配置信息$")
	public void 我应该成功创建新的图表配置信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertSuccess();
		
	}

	@假如("^我要查询刚刚创建的图表配置信息详情$")
	public void 我要查询刚刚创建的图表配置信息详情() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		this.id = JSONObject.fromObject(d).getInt("id");
		this.configName = JSONObject.fromObject(d).getString("configName");
		this.chartName = JSONObject.fromObject(d).getString("chartName");
		this.apiName = JSONObject.fromObject(d).getString("apiName");
		this.chartType = JSONObject.fromObject(d).getString("chartType");
		this.configName = JSONObject.fromObject(d).getString("configName");
		this.dimension = JSONObject.fromObject(d).getString("dimension");
		this.perfIndex = JSONObject.fromObject(d).getString("perfIndex");
		this.indexName = JSONObject.fromObject(d).getString("indexName");
		
	}

	@当("^我发送图表配置信息查询请求的时候$")
	public void 我发送图表配置信息查询请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.id;
        ApiTestCommon.res = ApiTestCommon.get(myUrl);
        ApiTestCommon.assertSuccess();
	}

	@假如("^我要把上面创建的图表配置信息的接口名称改为\"([^\"]*)\"$")
	public void 我要把上面创建的图表配置信息的接口名称改为(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.apiName=arg1;
	}

	@当("^我发送修改图表配置信息请求的时候$")
	public void 我发送修改图表配置信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        System.out.println(ApiTestCommon.res.get("data").toString());
		Map<String, Object> map = new HashMap<String, Object>(10);
		map.put("id", d.getInt("id"));
        map.put("apiName", this.apiName);
		map.put("configName", d.getString("configName"));
		map.put("chartName", d.getString("chartName"));
		map.put("chartType", d.getString("chartType"));
		map.put("configName", d.getString("configName"));
		map.put("indexName", d.getString("indexName"));
		map.put("dimension", d.getString("dimension"));
		map.put("perfIndex", d.getString("perfIndex"));
		
		JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);

        ApiTestCommon.res = ApiTestCommon.putJson(this.url, result);
	}

	@那么("^我应该能(\\d+)收到\"([^\"]*)\"$")
	public void 我应该能_收到(int arg1, String arg2) throws Exception {
		ApiTestCommon.assertMessage(arg2);
	}

	@假如("^我要删除上面创建的图表配置信息$")
	public void 我要删除上面创建的图表配置信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        this.id = d.getInt("id");
	}

	@当("^我发送删除图表配置信息请求的时候$")
	public void 我发送删除图表配置信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.id;
        System.out.println("delete: myUrl = " + myUrl);
        ApiTestCommon.res = ApiTestCommon.delete(myUrl);
	}

	@那么("^我应该要(\\d+)收到\"([^\"]*)\"$")
	public void 我应该要_收到(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg2);
	}

	@假如("^我想得到所有的图表配置信息$")
	public void 我想得到所有的图表配置信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@当("^我发送获取所有图表配置信息请求的时候$")
	public void 我发送获取所有图表配置信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.res = ApiTestCommon.get(this.url);
	}

}
