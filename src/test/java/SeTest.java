import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.CollectionStudy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SeTest {
	CollectionStudy collectionStudy;
	@BeforeEach
	public void beforeEach(){
		System.out.println("--------------测试对象实例化--------------");
		collectionStudy = new CollectionStudy();
	}

    @Test
    public void paixu(){

		Map<String, List<String>> stringListMap = collectionStudy.stringListMap();
        System.out.println("排序前————————————————————————————————————————————————");
		stringListMap.forEach((s, strings) -> {
			System.out.println(s+":"+strings.size());
		});
        ArrayList<Map.Entry<String, List<String>>> entries = Lists.newArrayList(stringListMap.entrySet());
        Comparator<Map.Entry<String, List<String>>> entryComparator = Comparator.comparingInt(o -> o.getValue().size());
        entries.sort(entryComparator);
        System.out.println("排序后————————————————————————————————————————————————");
        entries.forEach(stringListEntry -> {
            System.out.println(stringListEntry.getKey()+":"+stringListEntry.getValue().size());
        });


    }
}
