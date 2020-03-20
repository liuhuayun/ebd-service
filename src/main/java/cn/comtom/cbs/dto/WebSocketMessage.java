package cn.comtom.cbs.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebSocketMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2013433359440618776L;

	/**
	 * 消息类型
	 */
	private String type;
	
	/**
	 * 消息数据
	 */
	private Object data;
	
}
