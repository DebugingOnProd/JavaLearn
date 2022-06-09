package org.lhq.se;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
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
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lhq.entity.Address;
import org.lhq.entity.Person;
import org.lhq.utils.AsyncThreadPoolUtil;
import org.lhq.utils.DateUtil;
import org.lhq.utils.ThreadPoolUtils;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@DisplayName("JavaAPI测试")
class SeTest {

    CollectionStudy collectionStudy;

    private Map<String,String> cache = new HashMap();

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
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 5, -7, 8, 4, 6);
        CountDownLatch latch = new CountDownLatch(list.size());
        list.forEach(item -> new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(item);
                log.info("{}", item);
                latch.countDown();
            } catch (InterruptedException e) {
                log.error("线程被中断", e);
            }
        }).start());
        latch.await();
    }

    @Test
    @DisplayName("并发修改错误")
    void parallelModification() {
        HashMap<Double, Double> map = new HashMap<>();
        ListeningExecutorService guavaExecutor = AsyncThreadPoolUtil.getGuavaExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 100; i++) {
            guavaExecutor.execute(() -> map.put(Math.random(), Math.random()));
            countDownLatch.countDown();
            //log.info("添加成功:{33}");
        }
        map.forEach((key, value) -> log.info("{}:{}", key, value));
    }

    @Test
    @DisplayName("并发修改错误2")
    void parallelModification2() throws InterruptedException {
        Map<Double, Double> map = new ConcurrentHashMap<>();
        ListeningExecutorService guavaExecutor = AsyncThreadPoolUtil.getGuavaExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            guavaExecutor.execute(() -> map.put(Math.random(), Math.random()));
            countDownLatch.countDown();
        }
        countDownLatch.await();
        map.forEach((key, value) -> log.info("{}:{}", key, value));
    }

    @Test
    @DisplayName("深拷贝与浅拷贝")
    void cloneable() {
        Person person = new Person(new Address("hangzhou"));
        Person clone = person.clone();
        log.info("{}", person.getAddress() == clone.getAddress());
    }

    @Test
    void forEachTest() {
        List<Double> list = Arrays.asList(34.857, 35.637, 36.000, 36.286, 38.250, 38.667, 38.667, 41.231, 43.428, 43.428, 44.000);
        Double avg = list.stream().collect(Collectors.averagingDouble(Double::doubleValue));
        log.info("avg:{}", avg);
    }

    @Test
    void listToMap() throws FileNotFoundException {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\Users\\Hades\\Downloads\\方案规则和组件清单20220602-已分类 (1)(1).xls"), "规则清单");
        List<Map<String, Object>> readAll = reader.readAll();
        List<HashMap<@Nullable String, @Nullable Object>> collect = readAll.stream().map(item -> {
            HashMap<@Nullable String, @Nullable Object> map = Maps.newHashMap();
            map.put("id", item.get("   "));
            map.put("type", item.get("差错类别"));
            return map;
        }).toList();
        HashMap<@Nullable String, @Nullable String> typeMap = Maps.newHashMap();
        typeMap.put("规范类", "1");
        typeMap.put("不知情定制", "2");
        List<String> sqlList = collect.stream().map(item -> "update audit_rule_comp set error_type=" + typeMap.get(item.get("type")) + " where rule_comp_id = " + item.get("id")).toList();
        String allSql = Joiner.on(" union \n").join(sqlList);
        byte[] sqlBytes = allSql.getBytes(StandardCharsets.UTF_8);
        FileUtil.writeBytes(sqlBytes, "C:/Users/Hades/Documents/aisa_info/生成的SQL.sql");
        //log.info("读取到的数据{}",allSql);

    }
    @Test
    void cacheTest() throws InterruptedException {
        cache.put("0","缓存0");
        cache.put("1","缓存1");
        cache.put("2","缓存2");
        cache.put("3","缓存4");
        cache.put("4","缓存4");
        cache.put("5","缓存5");
        ConcurrentMap<String, Future<String>> concurrentMap = Maps.newConcurrentMap();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        AtomicInteger count = new AtomicInteger();

        for (int i = 0; i < 100; i++) {
            final int n = i % 5;
            Future<String> cacheData = concurrentMap.get(String.valueOf(n));
            Future<String> future =  ThreadPoolUtils.submit(() -> {
                if (cacheData == null) {
                    count.getAndIncrement();
                    return loadData(String.valueOf(n));
                } else {
                    return null;
                }
            });
            while (!future.isDone()){
                log.warn("线程还没有执行完成");
                TimeUnit.MICROSECONDS.sleep(200L);
            }
            concurrentMap.put(String.valueOf(n),future);
            countDownLatch.countDown();

        }
        countDownLatch.await();
        log.info(String.valueOf(concurrentMap));
        Set<Map.Entry<String, Future<String>>> entries = concurrentMap.entrySet();
        entries.forEach(item-> {
            try {
                log.info("key:{},value:{}", item.getKey(),  item.getValue().get(30L,TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                log.error("线程被中断",e);
            }
        });
        log.info("一共执行了{}次",count.get());
        log.info(String.valueOf(concurrentMap));

    }

    private String loadData(String key) {
        log.info("有人来获取数据:{}",key);
        try {
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
            log.error("线程被中断",e);
        }
        return cache.get(key);
    }


}
