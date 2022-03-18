package org.lhq.web.http;

import lombok.Data;

import java.util.Map;
@Data
public class HttpResponse {
	private String version; // http版本
	private int code;       // 响应码
	private String status;  // 状态信息
	private Map<String, String> headers; // 响应头
	private String body;    // 响应数据
	private byte[] data;
}
