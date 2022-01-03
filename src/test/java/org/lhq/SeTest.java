package org.lhq;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lhq.entity.User;
import org.lhq.se.CollectionStudy;
import org.lhq.utils.ConvertUtil;


import java.util.*;
import java.util.stream.Collectors;

@Slf4j
class SeTest {

	CollectionStudy collectionStudy;
	@BeforeEach
	public void beforeEach(){
		log.info("--------------测试对象实例化--------------");
		collectionStudy = new CollectionStudy();
	}

    @Test
	void paixu(){

		Map<String, List<String>> stringListMap = collectionStudy.stringListMap();
		log.info("-------排序前--------------");
		stringListMap.forEach((s, strings) -> {
			log.info(s+":"+strings.size());
		});
        ArrayList<Map.Entry<String, List<String>>> entries = Lists.newArrayList(stringListMap.entrySet());
        Comparator<Map.Entry<String, List<String>>> entryComparator = Comparator.comparingInt(o -> o.getValue().size());
        entries.sort(entryComparator);
        log.info("排序后———————————————");
        entries.forEach(stringListEntry -> log.info(stringListEntry.getKey()+":"+stringListEntry.getValue().size()));


    }
	@Test
	public void xx(){
		ArrayList<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId((long) i);
			user.setNickname(String.valueOf(i));
			user.setNickname(String.valueOf(i));
			users.add(user);
		}
		List<User> collect = users.stream().map(user -> {
			User newUser = new User();
			newUser.setNickname(user.getNickname());
			newUser.setNickname(user.getNickname());
			return newUser;
		}).collect(Collectors.toList());
		log.info(String.valueOf(collect));
	}


	@Test
	void xxl(){
		User user = new User();
		user.setNickname("wdf");
		List<String> strings = ConvertUtil.resultToList(Collections.singletonList(user), User::getNickname);
		log.info("{}",strings);
	}

	@Test
	void oneDayWork(){
		log.info("打开IDEA");
		log.info("构建数据库，链接tomcat，crud一顿输出");
		log.info("嘴角疯狂上扬");
		log.error("接口报错");
		log.info("心态崩了，卸载IDEA");
	}

}
