package org.lhq.se;


import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Wallace
 */
@Slf4j
public class CollectionStudy {

	public Map<String, List<String>> stringListMap(){
		log.info("生产map");
		HashMap<String, List<String>> stringListHashMap = new HashMap<>(16);
		ArrayList<String> strings = new ArrayList<>();
		ArrayList<String> strings2 = new ArrayList<>();
		ArrayList<String> strings3 = new ArrayList<>();
		ArrayList<String> strings4 = new ArrayList<>();
		Random random = new Random();
		for (int j = 0; j < random.nextInt(10); j++) {
			strings.add(IdUtil.fastSimpleUUID());
		}
		for (int j = 0; j < random.nextInt(10); j++) {
			strings2.add(IdUtil.fastSimpleUUID());
		}
		for (int j = 0; j < random.nextInt(10); j++) {
			strings3.add(IdUtil.fastSimpleUUID());
		}
		for (int j = 0; j < random.nextInt(10); j++) {
			strings4.add(IdUtil.fastSimpleUUID());
		}
		stringListHashMap.put("List1",strings);
		stringListHashMap.put("List2",strings2);
		stringListHashMap.put("List3",strings3);
		stringListHashMap.put("List4",strings4);
		return stringListHashMap;
	}

}
