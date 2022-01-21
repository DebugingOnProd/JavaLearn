package org.lhq.web.http;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServlet {
	public void doGet(HttpRequest request, HttpResponse response) {
		log.info(request.getUrl());
		response.setBody("<html><h1>Hello World!</h1></html>");
	}

	public void doPost(HttpRequest request, HttpResponse response) {

	}
}
