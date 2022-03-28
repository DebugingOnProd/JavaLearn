package org.lhq.design.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySqlDriver implements DriverAPI {
	@Override
	public void doConnect(String url, String username, String password) {
		log.info("mysql驱动");
	}
}
