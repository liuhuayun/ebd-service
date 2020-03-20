package cn.comtom.cbs.apitest;



import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



import net.sf.json.JSONObject;

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;

public class OperatorLog {
	private String url = ApiTestCommon.BASE_URL + "/operator/log";
	private String Id=null;
	private String sysId=null;
	private String orgId=null;
	private String module=null;
	private String operation=null;
	private String ocontext=null;
	private String oname=null;
	private String oaccount=null;
	private String ip=null;
	private String ipCity=null;
	private String logType=null;
	private String deviceName=null;
	private String browserName=null;
	private String createTime=null;
	@假如("^我要创建一个随机id,所属系统为\"([^\"]*)\",所属组织为\"([^\"]*)\",模块名称为\"([^\"]*)\",操作为\"([^\"]*)\",名字为\"([^\"]*)\",账号为\"([^\"]*)\",IP为\"([^\"]*)\",城市为\"([^\"]*)\",创建时间为\"([^\"]*)\",环境为\"([^\"]*)\",日志类型为\"([^\"]*)\",设备名称为\"([^\"]*)\",浏览器为\"([^\"]*)\"的操作用户信息$")
	public void 我要创建一个随机id_所属系统为_所属组织为_模块名称为_操作为_名字为_账号为_IP为_城市为_创建时间为_环境为_日志类型为_设备名称为_浏览器为_的操作用户信息(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13) throws Exception {
	     	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		//this.Id=arg1;
		this.sysId=arg1;
		this.orgId=arg2;
		this.module=arg3;
		this.operation=arg4;
		this.ocontext=arg10;
		this.oname=arg5;
		this.oaccount=arg6;
		this.ip=arg7;
		this.ipCity=arg8;
		this.createTime=arg9;
		this.logType=arg11;
		this.deviceName=arg12;
		this.browserName=arg13;
		this.Id=UUID.randomUUID().toString().substring(0,31);
	}

	@当("^我发送创建新的操作用户信息请求时$")
	public void 我发送创建新的操作用户信息请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.Id);
		map.put("sysId", this.sysId);
		map.put("orgId", this.orgId);
		map.put("module", this.module);
		map.put("operation", this.operation);
		map.put("ocontext", this.ocontext);
		map.put("oname", this.oname);
		map.put("oaccount", this.oaccount);
		map.put("ip", this.ip);
		map.put("ipCity", this.ipCity);
		map.put("createTime", this.createTime);
		map.put("logType", this.logType);
		map.put("deviceName", this.deviceName);
		map.put("browserName", this.browserName);
		   JSONObject jsonObject = JSONObject.fromObject(map);
		  // System.out.println("创建请求body：" + jsonObject);
			String result = jsonObject.toString();
			// String u = ApiTestCommon.BASE_URL + "/operator/log";
			String myUrl = this.url;
			ApiTestCommon.res = ApiTestCommon.postJson(myUrl, result);
			System.out.println("res: " + ApiTestCommon.res);
	}

	@那么("^我应该成功创建新的操作用户信息$")
	public void 我应该成功创建新的操作用户信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertSuccess();
	}

	@假如("^我要查询刚刚创建的操作用户信息详情$")
	public void 我要查询刚刚创建的操作用户信息详情() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		this.Id = JSONObject.fromObject(d).getString("id");
		this.sysId = JSONObject.fromObject(d).getString("sysId");
		this.orgId = JSONObject.fromObject(d).getString("orgId");
		this.module = JSONObject.fromObject(d).getString("module");
		this.operation = JSONObject.fromObject(d).getString("operation");
		this.ocontext = JSONObject.fromObject(d).getString("ocontext");
		this.oname = JSONObject.fromObject(d).getString("oname");
		this.oaccount = JSONObject.fromObject(d).getString("oaccount");
		this.ip = JSONObject.fromObject(d).getString("ip");
		this.ipCity = JSONObject.fromObject(d).getString("ipCity");
		this.logType = JSONObject.fromObject(d).getString("logType");
		this.deviceName = JSONObject.fromObject(d).getString("deviceName");
		this.browserName = JSONObject.fromObject(d).getString("browserName");
	}

	@当("^我发送操作用户信息查询请求的时候$")
	public void 我发送操作用户信息查询请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.Id;
        ApiTestCommon.res = ApiTestCommon.get(myUrl);
        //ApiTestCommon.assertSuccess();
	}

	@那么("^我应该收到\"([^\"]*)\"$")
	public void 我应该收到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        String n = JSONObject.fromObject(d).getString("oname");
        assertEquals(n, this.oname);
        String h = JSONObject.fromObject(d).getString("ip");
        assertEquals(h, this.ip);
	}

	@假如("^我要把上面创建的操作用户信息名字改为\"([^\"]*)\"$")
	public void 我要把上面创建的操作用户信息名字改为(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.oname = arg1;
	}

	@当("^我发送修改操作用户信息请求的时候$")
	public void 我发送修改操作用户信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        System.out.println(ApiTestCommon.res.get("data").toString());
		Map<String, Object> map = new HashMap<String, Object>(10);
		map.put("id", d.getString("id"));
        map.put("oname", this.oname);
        //map.put("id", this.Id);
		map.put("sysId", d.getString("sysId"));
		map.put("orgId", d.getString("orgId"));
		map.put("module", d.getString("module"));
		map.put("operation", d.getString("operation"));
		map.put("ocontext", d.getString("ocontext"));
		//map.put("oname", this.oname);
		map.put("oaccount", d.getString("oaccount"));
		map.put("ip", d.getString("ip"));
		map.put("ipCity", d.getString("ipCity"));
		map.put("createTime", d.getString("createTime"));
		map.put("logType", d.getString("logType"));
		map.put("deviceName", d.getString("deviceName"));
		map.put("browserName", d.getString("browserName"));
        JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);

        ApiTestCommon.res = ApiTestCommon.putJson(this.url, result);
	}
	@那么("^我应该能收到\"([^\"]*)\"$")
	public void 我应该能收到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	  //  throw new PendingException();
		ApiTestCommon.assertMessage(arg1);
	}

	@假如("^我要删除上面创建的用户登录$")
	public void 我要删除上面创建的用户登录() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        this.Id = d.getString("id");
	}

	@当("^我发送删除操作用户信息请求的时候$")
	public void 我发送删除操作用户信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.Id;
        System.out.println("delete: myUrl = " + myUrl);
        ApiTestCommon.res = ApiTestCommon.delete(myUrl);
	}
	@那么("^我应该要收到\"([^\"]*)\"$")
	public void 我应该要收到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg1);
	}
	@假如("^我想得到所有的操作用户信息$")
	public void 我想得到所有的操作用户信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@当("^我发送获取所有操作用户信息请求的时候$")
	public void 我发送获取所有操作用户信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}
	@那么("^我应该得到\"([^\"]*)\"$")
	public void 我应该得到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	 //   throw new PendingException();
		ApiTestCommon.res = ApiTestCommon.get(this.url);
	     ApiTestCommon.assertSuccess();
	}
}
