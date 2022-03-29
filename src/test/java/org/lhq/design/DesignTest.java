package org.lhq.design;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.lhq.design.adapter.AudioPlayer;
import org.lhq.design.bridge.DriverManger;
import org.lhq.design.bridge.DriverMangerImpl;
import org.lhq.design.bridge.MySqlDriver;
import org.lhq.design.builder.Builder;
import org.lhq.design.factory.ICommodity;
import org.lhq.design.factory.StoreFactory;
import org.lhq.design.factory.abstracts.CacheService;
import org.lhq.design.factory.abstracts.factory.impl.MySQlCacheAdapter;
import org.lhq.design.factory.abstracts.factory.impl.OracleCacheAdapter;
import org.lhq.design.factory.abstracts.factory.proxy.JDKProxy;
import org.lhq.design.observer.BinaryObserver;
import org.lhq.design.observer.HexaObserver;
import org.lhq.design.observer.OctalObserver;
import org.lhq.design.observer.Subject;

@Slf4j
public class DesignTest {
	@Test
	@DisplayName("测试工厂模式")
	void test() {
		StoreFactory storeFactory = new StoreFactory();
		ICommodity commodityService = storeFactory.getCommodityService(1);
		commodityService.sendCommodity("1","2","3",null);
		ICommodity commodityService2 = storeFactory.getCommodityService(2);
		commodityService2.sendCommodity("1","2","3",null);


	}
	@SneakyThrows
	@Test
	@DisplayName("抽象工厂模式")
	void absFactory(){
		CacheService mysqlProxy = JDKProxy.getProxy(CacheService.class, MySQlCacheAdapter.class);
		String mysql = mysqlProxy.get("");
		CacheService oracleProxy = JDKProxy.getProxy(CacheService.class, OracleCacheAdapter.class);
		String oracle = oracleProxy.get("");
	}
	@Test
	@DisplayName("建造者模式")
	void builder(){
		Builder builder = new Builder();
		// 豪华欧式
		log.info(builder.levelOne(132.52D).getDetail());
		// 轻奢田园
		log.info(builder.levelTwo(98.25D).getDetail());
		// 现代简约
		log.info(builder.levelThree(85.43D).getDetail());
	}

	@Test
	@DisplayName("适配器模式")
	void adapter(){
		AudioPlayer audioPlayer = new AudioPlayer();

		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}
	@Test
	@DisplayName("桥接模式")
	void bridge(){
		DriverManger driverManger =  new DriverMangerImpl("1","2","3",new MySqlDriver());
		driverManger.doDriver();
	}
	@Test
	@DisplayName("观察者模式")
	void observer(){
		Subject subject = new Subject();
		new HexaObserver(subject);
		new OctalObserver(subject);
		new BinaryObserver(subject);

		log.info("十进制数字:15");
		subject.setState(15);
		log.info("十进制数字:10");
		subject.setState(10);
	}
}
