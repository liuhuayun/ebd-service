package cn.comtom.cbs.apitest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cn.comtom.cbs.model.User;
import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import net.sf.json.JSONObject;

public class UserApiTest {
	
	private String url = ApiTestCommon.BASE_URL + "/user";
	
	private User testUser = new User();

	@假如("^我需要查看已注册用户信息$")
	public void 我需要查看已注册用户信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    ApiTestCommon.log.info("使用默认参数调用list接口");
	}

	@当("^我发送查看系统当前用户信息的请求时$")
	public void 我发送查看系统当前用户信息的请求时() throws Exception {
		// Write code here that turns the phrase above into concrete actions
	    ApiTestCommon.res = ApiTestCommon.get(this.url);
	    System.out.println(">>>>Service_response:"+ApiTestCommon.res);
	}


	@那么("^按理说我应该收到\"([^\"]*)\"$")
	public void 按理说我应该收到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    ApiTestCommon.assertSuccess();
	}
	
	@假如("^我现在要创建一个随机id,所属系统为\"([^\"]*)\",所属组织为\"([^\"]*)\",联系方式\"([^\"]*)\",昵称为\"([^\"]*)\",真实名字为\"([^\"]*)\"的用户$")
	public void 我现在要创建一个随机id用户信息(String arg1, String arg2, String arg3, String arg4, String arg5) throws Exception {
		testUser.setUserId(UUID.randomUUID().toString().substring(0, 31));
		testUser.setSystemId(arg1);
		testUser.setOrgId(arg2);
		testUser.setPhone(arg3);
		testUser.setUsername(arg4);
		testUser.setRealName(arg5);
	}

	@当("^我发送添加新用户的请求时$")
	public void 我发送添加新用户的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("createTime", String.valueOf(System.currentTimeMillis()));
	    map.put("systemId", testUser.getSystemId());	    
	    map.put("orgId", testUser.getOrgId());
	    map.put("phone", testUser.getPhone());
	    map.put("realName", testUser.getRealName());
	    map.put("userId", testUser.getUserId());
	    map.put("username", testUser.getUsername());
	    String requestBodyStr = JSONObject.fromObject(map).toString();
	    ApiTestCommon.res = ApiTestCommon.postJson(this.url, requestBodyStr);
	    System.out.println(">>>>Service_response:"+ApiTestCommon.res);
	}
	
	@假如 ("^我要把上面创建的操作用户信息真实名字改为\"([^\"]*)\"$")
	public void 更新用户信息(String arg1) throws Exception {
		testUser.setRealName(arg1);
	}
	
	@当 ("^我发送修改用户信息的请求时$")
	public void 我发送修改用户信息的请求时() throws Exception{
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		Map<String,Object> map = new HashMap<String,Object>();
	    map.put("createTime", String.valueOf(System.currentTimeMillis()));
	    map.put("systemId", testUser.getSystemId());	    
	    map.put("orgId", testUser.getOrgId());
	    map.put("phone", testUser.getPhone());
	    map.put("realName", testUser.getRealName());
	    map.put("userId", d.getString("userId"));
	    map.put("username", testUser.getUsername());
	    String requestBodyStr = JSONObject.fromObject(map).toString();
	    ApiTestCommon.res = ApiTestCommon.putJson(this.url, requestBodyStr);
	    System.out.println(">>>>Service_response:"+ApiTestCommon.res);
	}

	@假如("^我需要知道上面用户的详细信息$")
	public void 我需要知道上面用户的详细信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		ApiTestCommon.log.info("调用detail接口");
		String myurl = this.url +"/"+testUser.getUserId();
		ApiTestCommon.res = ApiTestCommon.get(myurl);
	    System.out.println(">>>>Service_response:"+ApiTestCommon.res);
	}

	@当("^我发送查看用户详情的请求时$")
	public void 我发送查看用户详情的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@假如("^我需要删除上面创建的用户$")
	public void 我需要删除上面创建的用户() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		ApiTestCommon.log.info("调用delete接口");
		String myurl = this.url +"/"+testUser.getUserId();
		ApiTestCommon.res = ApiTestCommon.delete(myurl);
	    System.out.println(">>>>Service_response:"+ApiTestCommon.res);
	}

	@当("^我发送删除用户信息的请求时$")
	public void 我发送删除用户信息的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@假如("^我需要按\"([^\"]*)\"来统计用户数量$")
	public void 我需要按_来统计用户数量(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		String myurl = this.url + "/audienceSummary?period="+arg1;
		System.out.println("请求地址-------->>"+myurl);	   
	    ApiTestCommon.res = ApiTestCommon.get(myurl);
	}

	@当("^我发统计用户数量的请求时$")
	public void 我发统计用户数量的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@假如("^我需要按\"([^\"]*)\"来统计新增用户数量$")
	public void 我需要按_来统计新增用户数量(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		String myurl = this.url + "/increaseUserSummary?period="+arg1;
		System.out.println("请求地址-------->>"+myurl);	   
	    ApiTestCommon.res = ApiTestCommon.get(myurl);
	}

	@当("^我发送统计新增用户数量的请求时$")
	public void 我发送统计新增用户数量的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@假如("^我需要按\"([^\"]*)\"来获取用户统计信息$")
	public void 我需要按_来获取用户统计信息(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		String myurl = this.url + "/summary?period="+arg1;
		System.out.println("请求地址-------->>"+myurl);	   
	    ApiTestCommon.res = ApiTestCommon.get(myurl);
	}

	@当("^我发送获取用户统计信息的请求时$")
	public void 我发送获取用户统计信息的请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		
	}


}
