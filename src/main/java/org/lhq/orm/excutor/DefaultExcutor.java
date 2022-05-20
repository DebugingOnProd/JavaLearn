package org.lhq.orm.excutor;

import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.User;
import org.lhq.orm.config.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DefaultExcutor  implements Excutor{
	private final Configuration configuration = new Configuration();
	@Override
	public <T> T query(String statement, Object parameter) {
		ResultSet set =null;
		PreparedStatement pre =null;
		try(Connection connection=getConnection()) {
			pre = connection.prepareStatement(statement);
			//设置参数
			pre.setString(1, parameter.toString());
			set = pre.executeQuery();
			User u=new User();
			//遍历结果集
			while(set.next()){

				u.setPassword(set.getString(3));
			}
			return (T) u;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if(set!=null){
					set.close();
				}if(pre!=null){
					pre.close();
				}
			}catch(Exception e2){
				log.error("关闭连接出错",e2);
			}
		}
		return null;
	}
	private Connection getConnection() {
		try {
			return configuration.build("config.xml");
		} catch (Exception e) {
			log.error("获取配置文件错误",e);
		}
		return null;
	}
}
