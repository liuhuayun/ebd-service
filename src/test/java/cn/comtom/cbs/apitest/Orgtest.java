package cn.comtom.cbs.apitest;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import net.sf.json.JSONObject;

public class Orgtest {
	private String url = ApiTestCommon.BASE_URL + "/org";
	private String orgId=null;
	private String orgName=null;
	//private String orgCode=null;
	private String parentId=null;
	private String systemId=null;
	private String createBy=null;
	private Boolean rooted=null;
	private Boolean leafed=null;
	private String contact=null;
	private String remark=null;
	private String createTime=null;
	private String updateTime=null;
	private String updateBy=null;
	private Boolean deleted=null;
	@假如("^我要创建一个id为\"([^\"]*)\",名称为\"([^\"]*)\",所属组织为\"([^\"]*)\",所属系统为\"([^\"]*)\",\"([^\"]*)\"为根节点,\"([^\"]*)\"为叶子节点,组织联系人为\"([^\"]*)\",备注为\"([^\"]*)\",创建时间为\"([^\"]*)\",创建者为\"([^\"]*)\",更新时间为为\"([^\"]*)\",修改者为\"([^\"]*)\",删除状态为\"([^\"]*)\"的组织$")
	public void 我要创建一个id为_名称为_所属组织为_所属系统为_为根节点_为叶子节点_组织联系人为_备注为_创建时间为_创建者为_更新时间为为_修改者为_删除状态为_的组织(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.orgId=arg1;
		//this.orgCode=arg2;
		this.orgName=arg2;
		this.parentId=arg3;
		this.systemId=arg4;
		this.createBy=arg10;
		this.rooted=true;
		this.leafed=true;
		this.contact=arg7;
		this.remark=arg8;
		this.createTime=arg9;
		this.updateTime=arg11;
		this.updateBy=arg12;
		this.deleted=true;
	}

	@当("^我发送创建新的组织请求时$")
	public void 我发送创建新的组织请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", this.orgId);
		//map.put("orgCode", this.orgCode);
		map.put("orgName", this.orgName);
		map.put("parentId", this.parentId);
		map.put("systemId", this.systemId);
		map.put("createBy", this.createBy);
		map.put("rooted", this.rooted);
		map.put("leafed", this.leafed);
		map.put("contact", this.contact);
		map.put("remark", this.remark);
		map.put("createTime", this.createTime);
		map.put("updateTime", this.updateTime);
		map.put("updateBy", this.updateBy);
		map.put("deleted", this.deleted);
	
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println("创建请求：" + jsonObject);
		String result = jsonObject.toString();

		String myUrl = this.url;
		ApiTestCommon.res = ApiTestCommon.postJson(myUrl, result);
		System.out.println("res: " + ApiTestCommon.res);
	}

	@那么("^我应该成功创建新的组织$")
	public void 我应该成功创建新的组织() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertSuccess();
	}

	@假如("^我要查询刚刚创建的组织详情$")
	public void 我要查询刚刚创建的组织详情() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
   		
   		this.orgId = JSONObject.fromObject(d).getString("orgId");
   		//this.orgCode = JSONObject.fromObject(d).getString("orgCode");
   		this.orgName = JSONObject.fromObject(d).getString("orgName");
   		this.parentId = JSONObject.fromObject(d).getString("parentId");
   		this.systemId = JSONObject.fromObject(d).getString("systemId");
   		this.createBy = JSONObject.fromObject(d).getString("createBy");
   		this.rooted = JSONObject.fromObject(d).getBoolean("rooted");
   		this.leafed = JSONObject.fromObject(d).getBoolean("leafed");
   		this.contact = JSONObject.fromObject(d).getString("contact");
   		this.remark = JSONObject.fromObject(d).getString("remark");
   		this.createTime = JSONObject.fromObject(d).getString("createTime");
   		this.updateTime = JSONObject.fromObject(d).getString("updateTime");
   		this.updateBy = JSONObject.fromObject(d).getString("updateBy");
   		this.deleted = JSONObject.fromObject(d).getBoolean("deleted");
	}

	@当("^我发送组织查询请求的时候$")
	public void 我发送组织查询请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.orgId;
        ApiTestCommon.res = ApiTestCommon.get(myUrl);
        ApiTestCommon.assertSuccess();
	}

	@那么("^我应该(\\d+)收到\"([^\"]*)\"$")
	public void 我应该_收到(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg2);
	}

	@假如("^我要把上面创建的组织名字改为\"([^\"]*)\"$")
	public void 我要把上面创建的组织名字改为(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.orgName = arg1;
	}

	@当("^我发送修改组织请求的时候$")
	public void 我发送修改组织请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
        System.out.println(ApiTestCommon.res.get("data").toString());
		Map<String, Object> map = new HashMap<String, Object>(20);
	    map.put("orgId", d.getString("orgId"));
	    //map.put("orgCode", this.orgCode);
	    map.put("orgName", this.orgName);
	    map.put("parentId", d.getString("parentId"));
	    map.put("systemId", d.getString("systemId"));
	    map.put("rooted", d.getBoolean("rooted"));
	    map.put("leafed", d.getBoolean("leafed"));
	    map.put("contact", d.getString("contact"));
	    map.put("remark", d.getString("remark"));
	    map.put("createTime", d.getString("createTime"));
	    map.put("updateTime", d.getString("updateTime"));
	    map.put("updateBy", d.getString("updateBy"));
	    map.put("deleted", d.getBoolean("deleted"));
	    map.put("createBy", d.getString("createBy"));
	    JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);
        ApiTestCommon.res = ApiTestCommon.putJson(this.url, result);
	}

	@那么("^我应该(\\d+)能收到\"([^\"]*)\"$")
	public void 我应该_能收到(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg2);
	}

	@假如("^我要删除上面创建的组织$")
	public void 我要删除上面创建的组织() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

	     this.orgId =d.getString("orgId");
	}

	@当("^我发送删除组织请求的时候$")
	public void 我发送删除组织请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.orgId;
        System.out.println("delete: myUrl = " + myUrl);
        ApiTestCommon.res = ApiTestCommon.delete(myUrl);
	}

	@那么("^我应该(\\d+)要收到\"([^\"]*)\"$")
	public void 我应该_要收到(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg2);
	}

	@假如("^我想得到所有的组织信息$")
	public void 我想得到所有的组织信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@当("^我发送获取所有组织信息请求的时候$")
	public void 我发送获取所有组织信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@那么("^我应该(\\d+)得到\"([^\"]*)\"$")
	public void 我应该_得到(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.res = ApiTestCommon.get(this.url);
	     ApiTestCommon.assertSuccess();
	}

}
