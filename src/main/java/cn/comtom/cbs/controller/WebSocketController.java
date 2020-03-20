/**
 * 
 */
package cn.comtom.cbs.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.comtom.cbs.core.Result;
import cn.comtom.cbs.core.ResultGenerator;
import cn.comtom.cbs.dto.WebSocketMessage;
import cn.comtom.cbs.service.SysParamsService;
import cn.comtom.cbs.service.WebSocket;
import cn.comtom.cbs.utils.VLCUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author william
 *
 */
@Api(value = "websocket相关接口", tags= {"websocket相关接口"} )
@RestController
@RequestMapping("/websocket")
@Data
@Slf4j
public class WebSocketController {
	
	@Autowired
	private SysParamsService sysParamsService;
	
	@GetMapping("/send")
	public Result<String> detail(@ApiParam(value = "消息字符串") @RequestParam(defaultValue = "") String message) {
		String jsonStr = "{\"type\":\"test\",\"value\":" + message + "}";
		try {
			WebSocket.sendInfo(jsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResultGenerator.genSuccessResult("Success!");
	}
	
	@ApiOperation(value = "应急广播平台推送最新消息通知")
	@PostMapping("/ebmNoticeSend")
	public Result<String> ebmNoticeSend(@RequestBody WebSocketMessage message) {
		log.info("应急广播平台websocket消息通知：{}",JSON.toJSONString(message));
		try {
			if("flvNotice".equals(message.getType())) {
				String DVBUrl = sysParamsService.getValByKey("DVBUrl");
				String destUrl = sysParamsService.getValByKey("DVB_RTMP_Url");
				VLCUtils.UDP2RTMP(DVBUrl, destUrl);
				message.setData(destUrl);
			}
			WebSocket.sendInfo(JSON.toJSONString(message));
		} catch (IOException e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("Error!".concat(e.getStackTrace().toString()));
		}
		return ResultGenerator.genSuccessResult("Success!");
	}
	
	@ApiOperation(value = "应急广播平台推送字幕插播通知")
	@PostMapping("/flvNoticeSend")
	public Result<String> flvNoticeSend(@RequestBody WebSocketMessage message) {
		try {
			String DVBUrl = sysParamsService.getValByKey("DVBUrl");
			String destUrl = sysParamsService.getValByKey("DVB_RTMP_Url");
			VLCUtils.UDP2RTMP(DVBUrl, destUrl);
			message.setData(destUrl);
			WebSocket.sendInfo(JSON.toJSONString(message));
		} catch (IOException e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("Error!".concat(e.getStackTrace().toString()));
		}
		return ResultGenerator.genSuccessResult("Success!");
	}
	
	@ApiOperation(value = "杀死ffgp进程")
	@PostMapping("/killFlvNotice")
	public Result<String> killFlvNotice(@RequestBody WebSocketMessage message) {
		try {
			VLCUtils.runSh(VLCUtils.STOP_FFMPEG);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("Error!".concat(e.getStackTrace().toString()));
		}
		return ResultGenerator.genSuccessResult("Success!");
	}

}
