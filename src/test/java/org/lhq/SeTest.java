package org.lhq;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lhq.se.CollectionStudy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
        log.info("排序后————————————————————————————————————————————————");
        entries.forEach(stringListEntry -> {
			log.info(stringListEntry.getKey()+":"+stringListEntry.getValue().size());
        });


    }
}
