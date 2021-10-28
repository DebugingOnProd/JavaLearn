package se;


import cn.hutool.core.util.IdUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Wallace
 */
public class CollectionStudy {

	public Map<String, List<String>> stringListMap(){
		HashMap<String, List<String>> stringListHashMap = new HashMap<>();
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
		System.out.println(strings.size());
		System.out.println(strings2.size());
		System.out.println(strings3.size());
		System.out.println(strings4.size());
		stringListHashMap.put("List1",strings);
		stringListHashMap.put("List2",strings2);
		stringListHashMap.put("List3",strings3);
		stringListHashMap.put("List4",strings4);
		return stringListHashMap;
	}

}
