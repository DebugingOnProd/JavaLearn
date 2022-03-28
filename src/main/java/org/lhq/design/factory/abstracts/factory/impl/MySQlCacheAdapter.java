package org.lhq.design.factory.abstracts.factory.impl;

import lombok.extern.slf4j.Slf4j;
import org.lhq.design.factory.abstracts.factory.ICacheAdapter;
import org.lhq.design.factory.abstracts.factory.db.MySQL;

@Slf4j
public class MySQlCacheAdapter implements ICacheAdapter  {
    MySQL sql = new MySQL();
	@Override
	public String get(String key) {
		log.info("MySql 适配器");
		return sql.getData(key);
	}
}
