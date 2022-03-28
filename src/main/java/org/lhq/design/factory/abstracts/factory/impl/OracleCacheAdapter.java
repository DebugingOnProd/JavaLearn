package org.lhq.design.factory.abstracts.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.abstracts.factory.ICacheAdapter;
import org.lhq.design.factory.abstracts.factory.db.Oracle;

@Slf4j
public class OracleCacheAdapter implements ICacheAdapter  {
	Oracle oracle = new Oracle();

	@Override
	public String get(String key) {
		log.info("Oracle 适配器");
		return oracle.getMap(key);
	}
}
