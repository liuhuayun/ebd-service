package cn.comtom.cbs.apitest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import net.sf.json.JSONObject;

public class Tmpltest {
	private String url = ApiTestCommon.BASE_URL + "/dashboard/tmpl";
	private String tmplName = "";
	private int blockNum = 0;
	private int searchPosition=0;
	private int id=0;
	@假如("^我要创建一个id为\"([^\"]*)\",模块名称为\"([^\"]*)\",块的个数为\"([^\"]*)\",所在的索引为\"([^\"]*)\"的模块$")
	public void 我要创建一个id为_模块名称为_块的个数为_所在的索引为_的模块(String arg1, String arg2, String arg3, String arg4) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	    this.id=Integer.parseInt(arg1);
		this.tmplName =arg2;
		this.blockNum= Integer.parseInt(arg3);
		this.searchPosition = Integer.parseInt(arg4);
	}

	@当("^我发送创建新的模块请求时$")
	public void 我发送创建新的模块请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		Map<String, Object> map = new HashMap<String, Object>(20);
		map.put("id",this.id);
		map.put("tmplName", this.tmplName);
		map.put("blockNum", this.blockNum);
		map.put("searchPosition", this.searchPosition);
		JSONObject jsonObject = JSONObject.fromObject(map);
		  // System.out.println("创建请求body：" + jsonObject);
			String result = jsonObject.toString();
			String myUrl = this.url;
			ApiTestCommon.res = ApiTestCommon.postJson(myUrl, result);
			System.out.println("res: " + ApiTestCommon.res);
	}

	@那么("^我应该成功创建新的模块$")
	public void 我应该成功创建新的模块() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertSuccess();
		
	}

	@假如("^我要查询刚刚创建的模块信息详情$")
	public void 我要查询刚刚创建的模块信息详情() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		
		this.tmplName = JSONObject.fromObject(d).getString("tmplName");
		this.blockNum = JSONObject.fromObject(d).getInt("blockNum");
		this.searchPosition = JSONObject.fromObject(d).getInt("searchPosition");
        this.id = d.getInt("id");
	}

	@当("^我发送操作模块查询请求的时候$")
	public void 我发送操作模块查询请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.id;
        ApiTestCommon.res = ApiTestCommon.get(myUrl);
        ApiTestCommon.assertSuccess();
	}

	@那么("^应该收到\"([^\"]*)\"$")
	public void 应该收到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        String n = JSONObject.fromObject(d).getString("tmplName");
        assertEquals(n, this.tmplName);
        int h = JSONObject.fromObject(d).getInt("searchPosition");
        assertEquals(h, this.searchPosition);
	}

	@假如("^我要把上面创建的模块的块数改为\"([^\"]*)\"$")
	public void 我要把上面创建的模块的块数改为(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.blockNum=Integer.parseInt(arg1);
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        System.out.println(ApiTestCommon.res.get("data").toString());
		Map<String, Object> map = new HashMap<String, Object>(10);
		map.put("id", d.getInt("id"));
		map.put("blockNum", this.blockNum);
		map.put("searchPosition", d.getInt("searchPosition"));
		map.put("tmplName", d.getString("tmplName"));
		
	}

	@当("^我发送修改模块信息请求的时候$")
	public void 我发送修改模块信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        System.out.println(ApiTestCommon.res.get("data").toString());
		Map<String, Object> map = new HashMap<String, Object>(10);
		map.put("id", d.getInt("id"));
		map.put("blockNum", this.blockNum);
		map.put("searchPosition", d.getInt("searchPosition"));
		map.put("tmplName", d.getString("tmplName"));
		JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);

        ApiTestCommon.res = ApiTestCommon.putJson(this.url, result);
	}

	@那么("^我能收到\"([^\"]*)\"$")
	public void 我能收到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg1);
	}

	@假如("^我要删除上面创建的模块$")
	public void 我要删除上面创建的模块() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        this.id = d.getInt("id");
	}

	@当("^我发送删除模块请求的时候$")
	public void 我发送删除模块请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.id;
        System.out.println("delete: myUrl = " + myUrl);
        ApiTestCommon.res = ApiTestCommon.delete(myUrl);
	}

	@那么("^我该收到\"([^\"]*)\"$")
	public void 我该收到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg1);
	}

	@假如("^我想得到所有的模块信息$")
	public void 我想得到所有的模块信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@当("^我发送获取所有模块信息请求的时候$")
	public void 我发送获取所有模块信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@那么("^要得到\"([^\"]*)\"$")
	public void 要得到(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.res = ApiTestCommon.get(this.url);
	     ApiTestCommon.assertSuccess();
	}
}
