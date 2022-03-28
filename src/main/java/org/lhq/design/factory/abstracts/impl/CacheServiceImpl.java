package org.lhq.design.factory.abstracts.impl;

import org.lhq.design.factory.abstracts.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheServiceImpl implements CacheService  {
	private static final Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Override
	public String get(String key) {
		log.info("根据key 获取缓存里面的值");
		return "默认值";
	}
}
