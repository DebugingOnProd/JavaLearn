import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SeTest {
    @Test
    public void paixu(){
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

        HashMap<List<String>, String> listObjectTreeMap = new HashMap<>();
        listObjectTreeMap.put(strings,"list1");
        listObjectTreeMap.put(strings2,"list2");
        listObjectTreeMap.put(strings3,"list3");
        listObjectTreeMap.put(strings4,"list4");
        System.out.println("排序前————————————————————————————————————————————————");
        listObjectTreeMap.forEach((strings1, s) -> {
            System.out.println(s+" "+strings1.size());
        });
        ArrayList<Map.Entry<String, List<String>>> entries = Lists.newArrayList(stringListHashMap.entrySet());
        Comparator<Map.Entry<String, List<String>>> entryComparator = Comparator.comparingInt(o -> o.getValue().size());
        entries.sort(entryComparator);
        System.out.println("排序后————————————————————————————————————————————————");
        entries.forEach(stringListEntry -> {
            System.out.println(stringListEntry.getKey()+":"+stringListEntry.getValue().size());
        });


    }
}
