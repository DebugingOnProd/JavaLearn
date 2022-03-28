package org.lhq.design.factory.abstracts.factory.db;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class Oracle {
	private Map<String, String> dataMap = new ConcurrentHashMap<String, String>();


	public String getMap(String key){
		log.info("从oracle数据库获取数据");
		return dataMap.get(key);
	}
}
