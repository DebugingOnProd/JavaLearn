package org.lhq.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.DbConfig;

import java.sql.*;

@Slf4j
public class JdbcUtil {
	private JdbcUtil(){}


	public static Connection getConnection(DbConfig dbConfig){
		try {
			Class.forName(dbConfig.getDriver());
			return DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
		} catch (SQLException | ClassNotFoundException e) {
			log.error(e.getMessage());
			return null;
		}
	}


	public static void close(ResultSet set){
		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
	}

	public static void close(Statement statement){
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
	}

	public static void close(Connection connection){
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
	}



	public static void close(Connection connection,Statement statement,ResultSet set){
		close(connection);
		close(statement);
		close(set);
	}



}
