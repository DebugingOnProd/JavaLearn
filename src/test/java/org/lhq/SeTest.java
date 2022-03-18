package org.lhq;

import cn.hutool.system.HostInfo;
import cn.hutool.system.JavaInfo;
import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.JavaSpecInfo;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.JvmSpecInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import cn.hutool.system.UserInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lhq.se.CollectionStudy;
import org.lhq.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
class SeTest {

	CollectionStudy collectionStudy;

	@BeforeEach
	public void beforeEach() {
		log.info("--------------测试对象实例化--------------");
		collectionStudy = new CollectionStudy();
	}

	@Test
	void paixu() {

		Map<String, List<String>> stringListMap = collectionStudy.stringListMap();
		log.info("-------排序前--------------");
		stringListMap.forEach((s, strings) -> {
			log.info(s + ":" + strings.size());
		});
		ArrayList<Map.Entry<String, List<String>>> entries = Lists.newArrayList(stringListMap.entrySet());
		Comparator<Map.Entry<String, List<String>>> entryComparator = Comparator.comparingInt(o -> o.getValue().size());
		entries.sort(entryComparator);
		log.info("排序后———————————————");
		entries.forEach(stringListEntry -> log.info(stringListEntry.getKey() + ":" + stringListEntry.getValue().size()));


	}


	@Test
	void oneDayWork() {
		log.info("打开IDEA");
		log.info("构建数据库，链接tomcat，crud一顿输出");
		log.info("嘴角疯狂上扬");
		log.error("接口报错");
		log.info("心态崩了，卸载IDEA");
	}

	@Test
	void jvmInfo() {
		JvmSpecInfo jvmSpecInfo = SystemUtil.getJvmSpecInfo();
		JvmInfo jvmInfo = SystemUtil.getJvmInfo();
		JavaSpecInfo javaSpecInfo = SystemUtil.getJavaSpecInfo();
		JavaInfo javaInfo = SystemUtil.getJavaInfo();
		JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
		OsInfo osInfo = SystemUtil.getOsInfo();
		UserInfo userInfo = SystemUtil.getUserInfo();
		RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
		HostInfo hostInfo = SystemUtil.getHostInfo();

		log.info(String.valueOf(jvmSpecInfo));
		log.info(String.valueOf(jvmInfo));
		log.info(String.valueOf(javaSpecInfo));
		log.info(String.valueOf(javaInfo));
		log.info(String.valueOf(javaRuntimeInfo));
		log.info(String.valueOf(osInfo));
		log.info(String.valueOf(userInfo));
		log.info(String.valueOf(runtimeInfo));
		log.info(String.valueOf(hostInfo));
	}
	@Test
	void threadLocalTest(){
		for (int i = 0; i < 20; i++) {
			new Thread(()-> DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")).start();
		}
	}

}
