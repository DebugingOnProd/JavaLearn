package org.lhq.se;

import cn.hutool.core.date.TimeInterval;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lhq.utils.AsyncThreadPoolUtil;
import org.lhq.utils.DateUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@DisplayName("JavaAPI测试")
class SeTest {

	CollectionStudy collectionStudy;

	@BeforeEach
	public void beforeEach() {
		log.info("--------------测试对象实例化--------------");
		collectionStudy = new CollectionStudy();
	}
	@AfterEach
	public void afterEach() {
		log.info("--------------测试用例结束--------------");
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
	@DisplayName("多线程测试")
	void threadLocalTest() {
		ArrayList<Object> list = Lists.newArrayList();
		TimeInterval timer = cn.hutool.core.date.DateUtil.timer();
		//CountDownLatch latch = new CountDownLatch(2000);
		for (int i = 0; i < 2000; i++) {
			//Future<String> dateString = AsyncThreadPoolUtil.getInstance().submit(() -> DateUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
			CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
						String formatDate = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
						//latch.countDown();
						return formatDate;
					},
					AsyncThreadPoolUtil.getInstance());
			//stringCompletableFuture.thenAccept(item -> log.info(String.valueOf(item)));

			stringCompletableFuture.thenAccept(list::add);

		}
		//latch.await();
		long l = timer.intervalRestart();

		ArrayList<String> list1 = Lists.newArrayList();
		timer.start();
		for (int i = 0; i < 2000; i++) {
			String formatDate = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
			list1.add(formatDate);
		}
		long restart = timer.intervalRestart();


		log.info("list大小:{}", list.size());
		log.info(String.valueOf(list));
		log.info("消耗时间:{}毫秒", l);
		log.info("++++++++++++++++++++++++++++++++++++++++++++++");
		log.info("list大小:{}", list1.size());
		log.info(String.valueOf(list1));
		log.info("消耗时间:{}毫秒", restart);

	}


	@Test
	void longTest() {
		long l = Long.parseLong("-1");
		log.info("{}", l);
	}
	@Test
	@DisplayName("睡眠排序法")
	void sleepSort() throws InterruptedException {
		ArrayList<Integer> list = Lists.newArrayList(1,2,5,-7,8,4,6);
		CountDownLatch latch = new CountDownLatch(list.size());
		list.forEach(item -> new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(item);
				log.info("{}",item);
				latch.countDown();
			} catch (InterruptedException e) {
				log.error("线程被中断",e);
			}
		}).start());
		latch.await();
	}






}
