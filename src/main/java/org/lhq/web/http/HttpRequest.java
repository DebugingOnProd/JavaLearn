package org.lhq.web.http;

import lombok.Data;

import java.util.Map;
@Data
public class HttpRequest {
	private String method;  // 请求方法
	private String url;     // 请求地址
	private String version; // http版本
	private Map<String, String> headers; // 请求头
	private String body;    // 请求主体
}
