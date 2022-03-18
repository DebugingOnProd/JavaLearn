package org.lhq.web.http;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
public class HttpServlet {
	HashMap<String,Method> methodMap =  new HashMap<>();
	HashMap<String,Class<?>> classHashMap = new HashMap<>();
	public void doGet(HttpRequest request, HttpResponse response) {
		doPost(request,response);
	}

	public void doPost(HttpRequest request, HttpResponse response) {
		log.info(request.getUrl());
		log.info("请求方法:{},请求地址{},",request.getMethod(),request.getUrl());
		String url = request.getUrl();
		String[] splitUrl = url.split("/");
		response.setBody("<html><h1>Hello World!</h1></html>");
	}
}
