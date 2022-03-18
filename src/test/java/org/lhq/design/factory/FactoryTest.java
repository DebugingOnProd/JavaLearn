package org.lhq.design.factory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lhq.design.factory.abstracts.CacheService;
import org.lhq.design.factory.abstracts.factory.impl.EGMCacheAdapter;
import org.lhq.design.factory.abstracts.factory.proxy.JDKProxy;
import org.lhq.design.factory.abstracts.impl.CacheServiceImpl;

@Slf4j
public class FactoryTest {
	@Test
	@DisplayName("测试工厂模式")
	void test() {
		StoreFactory storeFactory = new StoreFactory();
		ICommodity commodityService = storeFactory.getCommodityService(1);
		commodityService.sendCommodity("1","2","3",null);
		ICommodity commodityService2 = storeFactory.getCommodityService(2);
		commodityService2.sendCommodity("1","2","3",null);


	}
	@Test
	void absFactory(){
		//JDKProxy.getProxy(CacheServiceImpl.class,new EGMCacheAdapter())
	}
}
