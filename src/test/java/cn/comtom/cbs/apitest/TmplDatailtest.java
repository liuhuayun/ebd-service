package cn.comtom.cbs.apitest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;



import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import net.sf.json.JSONObject;

public class TmplDatailtest {
	private String url = ApiTestCommon.BASE_URL + "/dashboard/tmpl/detail";
	private int i = 0;
	private int x = 0;
	private int y = 0;
	private int h = 0;
	private int w = 0;
	private int tmplId = 0;
	private int id=0;
	@假如("^我要创建一个id为\"([^\"]*)\",模块id为\"([^\"]*)\",x坐标为\"([^\"]*)\",y坐标为\"([^\"]*)\",宽度为\"([^\"]*)\"，高度为\"([^\"]*)\",索引为\"([^\"]*)\"的仪表数据详细信息$")
	public void 我要创建一个id为_模块id为_x坐标为_y坐标为_宽度为_高度为_索引为_的仪表数据详细信息(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.id =Integer.parseInt(arg1);
		this.tmplId = Integer.parseInt(arg2);
		this.x = Integer.parseInt(arg3);
		this.y = Integer.parseInt(arg4);
		this.w = Integer.parseInt(arg5);
		this.h = Integer.parseInt(arg6);
		this.i = Integer.parseInt(arg7);
	}

	@当("^我发送创建新的仪表数据信息请求时$")
	public void 我发送创建新的仪表数据信息请求时() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		Map<String, Object> map = new HashMap<String, Object>(20);
		map.put("id", this.id);
		map.put("tmplId", this.tmplId);
		map.put("x", this.x);
		map.put("y", this.y);
		map.put("w", this.w);
		map.put("h", this.h);
		map.put("i", this.i);

		JSONObject jsonObject = JSONObject.fromObject(map);
		//System.out.println("创建请求body：" + jsonObject);
		String result = jsonObject.toString();

		String myUrl = this.url;
		ApiTestCommon.res = ApiTestCommon.postJson(myUrl, result);
		System.out.println("res: " + ApiTestCommon.res);
	}

	@那么("^我应该成功创建新的仪表数据信息$")
	public void 我应该成功创建新的仪表数据信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertSuccess();
	}

	@假如("^我要查询刚刚创建的仪表数据详情$")
	public void 我要查询刚刚创建的仪表数据详情() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));
		
		this.x = JSONObject.fromObject(d).getInt("x");
		this.y = JSONObject.fromObject(d).getInt("y");
		this.w = JSONObject.fromObject(d).getInt("w");
		this.h = JSONObject.fromObject(d).getInt("h");
		this.i = JSONObject.fromObject(d).getInt("i");

        this.id = d.getInt("id");
	}

	@当("^我发送操作仪表数据查询请求的时候$")
	public void 我发送操作仪表数据查询请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.id;
        ApiTestCommon.res = ApiTestCommon.get(myUrl);
        ApiTestCommon.assertSuccess();
	}

	@那么("^获得\"([^\"]*)\"$")
	public void 获得(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        int n = JSONObject.fromObject(d).getInt("h");
        assertEquals(n, this.h);
        int h = JSONObject.fromObject(d).getInt("w");
        assertEquals(h, this.w);
	}

	@假如("^我要把上面创建的仪表数据x坐标改为\"([^\"]*)\"$")
	public void 我要把上面创建的仪表数据x坐标改为(String arg1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		this.x =Integer.parseInt(arg1);
	}

	@当("^我发送修改仪表数据信息请求的时候$")
	public void 我发送修改仪表数据信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        System.out.println(ApiTestCommon.res.get("data").toString());
		Map<String, Object> map = new HashMap<String, Object>(10);
		map.put("id", d.getInt("id"));
		map.put("x", this.x);
		map.put("y", d.getInt("y"));
		map.put("w", d.getInt("w"));
		map.put("h", d.getInt("h"));
		map.put("i", d.getInt("i"));
		map.put("tmplId", d.getInt("tmplId"));
		JSONObject jsonObject = JSONObject.fromObject(map);
        String result = jsonObject.toString();
        System.out.println(result);

        ApiTestCommon.res = ApiTestCommon.putJson(this.url, result);
	}

	@那么("^应该(\\d+)获得\"([^\"]*)\"$")
	public void 应该_获得(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg2);
	}

	@假如("^我要删除上面创建的仪表数据$")
	public void 我要删除上面创建的仪表数据() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		JSONObject d = JSONObject.fromObject(ApiTestCommon.res.get("data"));

        this.id = d.getInt("id");
	}

	@当("^我发送删除仪表数据请求的时候$")
	public void 我发送删除仪表数据请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		String myUrl = this.url + "/" + this.id;
        System.out.println("delete: myUrl = " + myUrl);
        ApiTestCommon.res = ApiTestCommon.delete(myUrl);
	}

	@那么("^我应该(\\d+)获得\"([^\"]*)\"$")
	public void 我应该_获得(int arg1, String arg2) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.assertMessage(arg2);
	}

	@假如("^我想得到所有的仪表数据信息$")
	public void 我想得到所有的仪表数据信息() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@当("^我发送获取所有仪表数据信息请求的时候$")
	public void 我发送获取所有仪表数据信息请求的时候() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		ApiTestCommon.res = ApiTestCommon.get(this.url);
	     ApiTestCommon.assertSuccess();
	}
}
