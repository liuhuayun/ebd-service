package cn.comtom.cbs.apitest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import net.sf.json.JSONObject;

public class StatsUserLogin {

	private String url = ApiTestCommon.BASE_URL+"/stats/user";
	
	private String clientType;
	private String loginDate;
	private String loginId;
	private String userId;
	
	@假如("^我想获取当前登录系统所有用户信息$")
	public void 我想获取当前登录系统所有用户信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		ApiTestCommon.log.info("使用默认参数调用list接口");
	}

	@当("^我发送获取当前登录系统所有用户的请求时$") 
	public void 我发送获取当前登录系统所有用户的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	    ApiTestCommon.res = ApiTestCommon.get(url);
	}
	
	@那么("^我应该是可以收到\"([^\"]*)\"$")
	public void 我应该是可以收到(String arg1) throws Exception {
		ApiTestCommon.assertSuccess();
	}

	@假如("^user_id为\"([^\"]*)\"的用户即将通过\"([^\"]*)\"登录系统$")
	public void user_id为_的用户即将通过_登录系统(String arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		this.userId = arg1;
		this.clientType = arg2;
		this.loginDate = String.valueOf(System.currentTimeMillis());
		this.loginId = UUID.randomUUID().toString().substring(0,31);
	}

	@当("^该用户发送登录的请求时$")
	public void 该用户发送登录的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    Map<String,Object> requestBody = new HashMap<String, Object>();
	    requestBody.put("clientType", this.clientType);
	    requestBody.put("loginDate", this.loginDate);
	    requestBody.put("userId", this.userId);
	    requestBody.put("loginId", this.loginId);
	    String requestBodyStr = JSONObject.fromObject(requestBody).toString();
	    System.out.println("请求体------->>>>>>"+requestBodyStr);
	    ApiTestCommon.res = ApiTestCommon.postJson(url,requestBodyStr);
	    
	}

	@假如("^上面的用户将登录系统的方式改为\"([^\"]*)\"$")
	public void 上面的用户将登录系统的方式改为(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    this.clientType = "IOS";
	}

	@当("^该用户再次发送登录的请求时$")
	public void 该用户再次发送登录的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		Map<String,Object> requestBody = new HashMap<String, Object>();
	    requestBody.put("clientType", this.clientType);
	    requestBody.put("loginDate", String.valueOf(System.currentTimeMillis()));
	    requestBody.put("userId", d.getString("userId"));
	    requestBody.put("loginId", d.getString("loginId"));
	    String requestBodyStr = JSONObject.fromObject(requestBody).toString();
	    System.out.println("请求体------->>>>>>"+requestBodyStr);
	    ApiTestCommon.res = ApiTestCommon.putJson(url,requestBodyStr);
	}

	@假如("^我需要获取到上面的用户的登录信息$")
	public void 我需要获取到上面的用户的登录信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		ApiTestCommon.log.info("调用detail接口");
	}

	@当("^我发送的请求时$")
	public void 我发送的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		String id = d.getString("loginId");
		String myurl = this.url + "/" + id;
		ApiTestCommon.res = ApiTestCommon.get(myurl);
		System.out.println(ApiTestCommon.res);
	}

	@假如("^我需要把上面的用户登录信息删除$")
	public void 我需要把上面的用户登录信息删除() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		ApiTestCommon.log.info("调用delete接口");
	}

	@当("^该我发送删除的请求时$") 
	public void 该我发送删除的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		String id = d.getString("loginId");
		String myurl = this.url + "/" + id;
		ApiTestCommon.res = ApiTestCommon.delete(myurl);
	}

	@假如("^我需要一次性添加\"([^\"]*)\"个用户的登录信息时$")
	public void 我需要一次性添加_个用户的登录信息时(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		
		String myurl = this.url + "/batch?count="+arg1;
		System.out.println("请求地址-------->>"+myurl);
		ApiTestCommon.res = ApiTestCommon.post(myurl);
	}

	@当("^我发送批量添加的请求时$")
	public void 我发送批量添加的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@假如("^我需要查看\"([^\"]*)\"至\"([^\"]*)\"的登录用户信息$")
	public void 我需要查看_至_的登录用户信息(String arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		String myurl = this.url + "/summary?beginDate="+arg1+"&endDate="+arg2+"&sumType=day";
		System.out.println("请求地址-------->>"+myurl);	   
	    ApiTestCommon.res = ApiTestCommon.get(myurl);
	}

	@当("^我发送查看某个时间段的用户登录信息的请求时$")
	public void 我发送查看某个时间段的用户登录信息的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@假如("^我需要查看本周用户活跃数,此时需指定\"([^\"]*)\"$")
	public void 我需要查看本周用户活跃数_此时需指定(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		String myurl = this.url + "/total?period="+arg1;
		System.out.println("请求地址-------->>"+myurl);	   
	    ApiTestCommon.res = ApiTestCommon.get(myurl);
	}

	@当("^我发送查看本周活跃用户数的请求时$")
	public void 我发送查看本周活跃用户数的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
}
