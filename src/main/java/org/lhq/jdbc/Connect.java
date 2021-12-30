package org.lhq.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Connect {
	private String url;
	private String username;
	private String pwd;

	public Connection conn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection(url,username,pwd);
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e.getMessage());
		}
	}
}
