package org.lhq;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lhq.entity.User;
import org.lhq.se.CollectionStudy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class SeTest {

	CollectionStudy collectionStudy;
	@BeforeEach
	public void beforeEach(){
		log.info("--------------测试对象实例化--------------");
		collectionStudy = new CollectionStudy();
	}

    @Test
    public void paixu(){

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
			user.setUserId((long) i);
			user.setUsername(String.valueOf(i));
			user.setNickname(String.valueOf(i));
			users.add(user);
		}
		List<User> collect = users.stream().map(user -> {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setNickname(user.getNickname());
			return newUser;
		}).collect(Collectors.toList());
		log.info(String.valueOf(collect));
	}

}
