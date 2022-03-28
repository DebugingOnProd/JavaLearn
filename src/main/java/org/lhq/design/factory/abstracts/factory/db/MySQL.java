package org.lhq.design.factory.abstracts.factory.db;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
public class MySQL {
	private Map<String, String> dataMap = new ConcurrentHashMap<>();
	public String getData(String key){
		log.info("从 MySql 数据库获取数据");
		return dataMap.get(key);
	}
}
