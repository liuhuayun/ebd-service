 package cn.comtom.cbs.apitest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.Before;
import net.sf.json.JSONObject;

/**
 * cucumber测试的公共类；定义子常用的接口调用方法
 * @author william
 *
 */
public class ApiTestCommon {
	public final static String BASE_URL = "http://localhost:8088";
	// public final static String BASE_URL= "http://tape.pureinfo.cc";

	public static JSONObject res = null;
	public static final Logger log = LoggerFactory.getLogger(ApiTestCommon.class);


	@Before
	public void setUp() throws Exception {

	}

	public static JSONObject get(String url) throws Exception {
		log.info(url);
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		log.info(responseEntity.getBody().toString());

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject post(String url, MultiValueMap<String, Object> map) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<?> entity = new HttpEntity<Object>(map, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject postJson(String url, Map<String, Object> map) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<?> entity = new HttpEntity<Object>(map, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject postJson(String url, String jsonString) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// MediaType mediaType = MediaType.parseMediaType("application/json;
		// charset=UTF-8");
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject post(String url) {
		return ApiTestCommon.post(url, null);
	}

	public static JSONObject upload(String url, MultiValueMap<String, Object> map) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject put(String url, MultiValueMap<String, String> map) throws Exception {
		log.info(url);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject putJson(String url, Map<String, String> map) throws Exception {
		log.info(url);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(map, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject putJson(String url, String jsonString) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// MediaType mediaType = MediaType.parseMediaType("application/json;
		// charset=UTF-8");
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject put(String url) throws Throwable {
		return ApiTestCommon.put(url, null);
	}

	public static JSONObject delete(String myUrl) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(myUrl, HttpMethod.DELETE, requestEntity,
				String.class);
		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject userSignupSignin(String url, MultiValueMap<String, String> map) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map,
				headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static JSONObject userTeacherSignup(String mobile, String password) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("mobile", mobile);
		map.add("password", password);
		map.add("code", "111111");

		String url = ApiTestCommon.BASE_URL + "/user_teacher/signup";
		return ApiTestCommon.userSignupSignin(url, map);
	}

	public static JSONObject userParentSignup(String mobile, String password) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("mobile", mobile);
		map.add("password", password);
		map.add("code", "111111");

		String url = ApiTestCommon.BASE_URL + "/user_parent/signup";
		return ApiTestCommon.userSignupSignin(url, map);
	}

	public static JSONObject userAdminSignin(String url, Map<String, Object> map) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<Map<String, Object>>(map, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				String.class);

		return JSONObject.fromObject(responseEntity.getBody());
	}

	public static void assertCodeAndMessage(int code, String message) {
		ApiTestCommon.printRes();

		int s = (int) res.get("code");
		assertEquals(s, code);

		String m = res.get("message").toString();
		assertEquals(m, message);
	}

	public static void assertSuccess() {
		ApiTestCommon.assertCodeAndMessage(200, "SUCCESS");
	}

	public static void assertFail() {
		ApiTestCommon.printRes();
		int s = (int) res.get("code");
		assertNotEquals(200, s);
	}

	public static void assertMessage(String message) {
		ApiTestCommon.printRes();

		String m = res.get("message").toString();
		assertEquals(m, message);
	}

	public static void printRes() {
		log.info("Res: " + ApiTestCommon.res.toString());
	}
}
