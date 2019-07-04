package com.ren.mbshop.common.utils;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ServiceInfoUtil implements ApplicationListener<WebServerInitializedEvent> {

	private int port;
	private String webServer;

	@Override
	public void onApplicationEvent(WebServerInitializedEvent server) {
		// TODO Auto-generated method stub
		this.port = server.getWebServer().getPort();
		this.webServer = server.getApplicationContext().getWebServer().toString();
	}

	public int getPort() {
		return this.port;
	}

	public String getWebServer() {
		return this.webServer;
	}
}
