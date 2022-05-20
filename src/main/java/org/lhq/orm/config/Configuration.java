package org.lhq.orm.config;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lhq.exception.FuckException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Configuration {
	private static final ClassLoader loader = ClassLoader.getSystemClassLoader();

	public Connection build(String resource) {
		try {
			InputStream stream = loader.getResourceAsStream(resource);
			SAXReader reader = new SAXReader();
			reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
			Document document = reader.read(stream);
			Element root = document.getRootElement();
			return evalDataSource(root);
		} catch (Exception e) {
			throw new FuckException("error occured while evaling xml " + resource);
		}
	}

	private Connection evalDataSource(Element node) throws ClassNotFoundException {
		if (!node.getName().equals("database")) {
			throw new FuckException("root should be <database>");
		}
		String driverClassName = null;
		String url = null;
		String username = null;
		String password = null;
		//获取属性节点
		for (Element item : node.elements("property")) {
			String value = getValue(item);
			String name = item.attributeValue("name");
			if (name == null || value == null) {
				throw new FuckException("[database]: <property> should contain name and value");
			}
			//赋值
			switch (name) {
				case "url" -> url = value;
				case "username" -> username = value;
				case "password" -> password = value;
				case "driverClassName" -> driverClassName = value;
				default -> throw new FuckException("[database]: <property> unknown name");
			}
		}

		Class.forName(driverClassName);
		Connection connection = null;
		try {
			//建立数据库链接
			url = Optional.ofNullable(url).orElse("");
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			log.error("在建立数据库链接时发生错误",e);
		}
		return connection;
	}

	//获取property属性的值,如果有value值,则读取 没有设置value,则读取内容
	private String getValue(Element node) {
		return node.hasContent() ? node.getText() : node.attributeValue("value");
	}

	@SuppressWarnings("rawtypes")
	public MapperBean readMapper(String path) {
		MapperBean mapper = new MapperBean();
		try {
			InputStream stream = loader.getResourceAsStream(path);
			SAXReader reader = new SAXReader();
			reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
			Document document = reader.read(stream);
			Element root = document.getRootElement();
			mapper.setInterfaceName(root.attributeValue("nameSpace").trim()); //把mapper节点的nameSpace值存为接口名
			List<Function> list = new ArrayList<>(); //用来存储方法的List
			for (Iterator rootIter = root.elementIterator(); rootIter.hasNext(); ) {//遍历根节点下所有子节点
				Function fun = new Function();    //用来存储一条方法的信息
				Element e = (Element) rootIter.next();
				String sqlType = e.getName().trim();
				String funcName = e.attributeValue("id").trim();
				String sql = e.getText().trim();
				String resultType = e.attributeValue("resultType").trim();
				fun.setSqlType(sqlType);
				fun.setFuncName(funcName);
				Object newInstance = Class.forName(resultType).getDeclaredConstructor().newInstance();
				fun.setResultType(newInstance);
				fun.setSql(sql);
				list.add(fun);
			}
			mapper.setList(list);

		} catch (Exception e) {
			log.error("配置处理错误",e);
		}
		return mapper;
	}

}
