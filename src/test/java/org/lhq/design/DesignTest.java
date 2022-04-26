package org.lhq.design;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.lhq.design.adapter.AudioPlayer;
import org.lhq.design.agent.dao.UserDao;
import org.lhq.design.bridge.DriverManger;
import org.lhq.design.bridge.DriverMangerImpl;
import org.lhq.design.bridge.MySqlDriver;
import org.lhq.design.bridge.pay.channel.AliPay;
import org.lhq.design.bridge.pay.channel.WxPay;
import org.lhq.design.bridge.pay.mode.PayFace;
import org.lhq.design.bridge.pay.mode.PayFingerprint;
import org.lhq.design.builder.Builder;
import org.lhq.design.composite.aggregates.TreeRich;
import org.lhq.design.composite.service.engine.IEngine;
import org.lhq.design.composite.service.engine.impl.TreeNodeHandle;
import org.lhq.design.composite.vo.EngineResult;
import org.lhq.design.composite.vo.TreeNode;
import org.lhq.design.composite.vo.TreeNodeLink;
import org.lhq.design.composite.vo.TreeRoot;
import org.lhq.design.corp.AbstractLogger;
import org.lhq.design.corp.ConsoleLogger;
import org.lhq.design.corp.DebugLogger;
import org.lhq.design.corp.ErrorLogger;
import org.lhq.design.decorator.LoginSsoDecorator;
import org.lhq.design.decorator.SsoInterceptor;
import org.lhq.design.factory.ICommodity;
import org.lhq.design.factory.StoreFactory;
import org.lhq.design.factory.abstracts.CacheService;
import org.lhq.design.factory.abstracts.factory.impl.MySQlCacheAdapter;
import org.lhq.design.factory.abstracts.factory.impl.OracleCacheAdapter;
import org.lhq.design.factory.abstracts.factory.proxy.JDKProxy;
import org.lhq.design.observer.BinaryObserver;
import org.lhq.design.observer.HexaObserver;
import org.lhq.design.observer.OctalObserver;
import org.lhq.design.observer.Subject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DesignTest {
    @Test
    @DisplayName("测试工厂模式")
    void test() {
        StoreFactory storeFactory = new StoreFactory();
        ICommodity commodityService = storeFactory.getCommodityService(1);
        commodityService.sendCommodity("1", "2", "3", null);
        ICommodity commodityService2 = storeFactory.getCommodityService(2);
        commodityService2.sendCommodity("1", "2", "3", null);


    }

    @SneakyThrows
    @Test
    @DisplayName("抽象工厂模式")
    void absFactory() {
        CacheService mysqlProxy = JDKProxy.getProxy(CacheService.class, MySQlCacheAdapter.class);
        String mysql = mysqlProxy.get("");
        CacheService oracleProxy = JDKProxy.getProxy(CacheService.class, OracleCacheAdapter.class);
        String oracle = oracleProxy.get("");
    }

    @Test
    @DisplayName("建造者模式")
    void builder() {
        Builder builder = new Builder();
        // 豪华欧式
        log.info(builder.levelOne(132.52D).getDetail());
        // 轻奢田园
        log.info(builder.levelTwo(98.25D).getDetail());
        // 现代简约
        log.info(builder.levelThree(85.43D).getDetail());
    }

    @Test
    @DisplayName("适配器模式")
    void adapter() {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }

    @Test
    @DisplayName("桥接模式")
    void bridge() {
        DriverManger driverManger = new DriverMangerImpl("1", "2", "3", new MySqlDriver());
        Assertions.assertNotNull(driverManger);
        driverManger.doDriver();
    }

    /**
     * 当一个对象发生改变的时候，所有依赖他的对象都会被通知到,并自动更新
     * <p>
     * 在抽象类中放一个数组来存放观察者们
     * <p>
     * 注意 ：
     * java中已经对Observer类,
     * 避免循环引用
     * 如果循序执行如果一个错误就会影响下面的执行，最好用异步执行
     */
    @Test
    @DisplayName("观察者模式")
    void observer() {
        Subject subject = new Subject();
        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        log.info("十进制数字:15");
        subject.setState(15);
        log.info("十进制数字:10");
        subject.setState(10);
    }

    @Test
    @DisplayName("责任链模式")
    void chainPattern() {
        AbstractLogger chainOfLoggers = getChainOfLoggers();
        chainOfLoggers.logMessage(AbstractLogger.INFO, "infoMessage");
        chainOfLoggers.logMessage(AbstractLogger.ERROR, "errorMessage");
        chainOfLoggers.logMessage(AbstractLogger.DEBUG, "debugMessage");
    }

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);
        errorLogger.setNextLogger(consoleLogger);
        consoleLogger.setNextLogger(debugLogger);
        return errorLogger;
    }

    @Test
    @DisplayName("代理模式测试")
    void agentTest() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);
        userDao.queryUserById(1L);
    }


    @Test
    @DisplayName("桥接模式测试2")
    void bridgeTest2() {
        log.info("支付宝转账,刷脸支付");
        AliPay aliPay = new AliPay(new PayFace());
        aliPay.transfer("老王", "001", new BigDecimal(100));

        log.info("微信支付,指纹");
        WxPay wxPay = new WxPay(new PayFingerprint());
        wxPay.transfer("小王", "002", new BigDecimal(200));
    }


    private TreeRich treeRich;


    @BeforeEach()
    void dataInit() {
        // 节点：1
        TreeNode treeNode_01 = new TreeNode();
        treeNode_01.setTreeId(10001L);
        treeNode_01.setTreeNodeId(1L);
        treeNode_01.setNodeType(1);
        treeNode_01.setNodeValue(null);
        treeNode_01.setRuleKey("userGender");
        treeNode_01.setRuleDesc("用户性别[男/女]");

        // 链接：1->11
        TreeNodeLink treeNodeLink_11 = new TreeNodeLink();
        treeNodeLink_11.setNodeIdFrom(1L);
        treeNodeLink_11.setNodeIdTo(11L);
        treeNodeLink_11.setRuleLimitType(1);
        treeNodeLink_11.setRuleLimitValue("man");

        // 链接：1->12
        TreeNodeLink treeNodeLink_12 = new TreeNodeLink();
        treeNodeLink_12.setNodeIdFrom(1L);
        treeNodeLink_12.setNodeIdTo(12L);
        treeNodeLink_12.setRuleLimitType(1);
        treeNodeLink_12.setRuleLimitValue("woman");

        List<TreeNodeLink> treeNodeLinkList_1 = new ArrayList<>();
        treeNodeLinkList_1.add(treeNodeLink_11);
        treeNodeLinkList_1.add(treeNodeLink_12);

        treeNode_01.setTreeNodeLinkList(treeNodeLinkList_1);

        // 节点：11
        TreeNode treeNode_11 = new TreeNode();
        treeNode_11.setTreeId(10001L);
        treeNode_11.setTreeNodeId(11L);
        treeNode_11.setNodeType(1);
        treeNode_11.setNodeValue(null);
        treeNode_11.setRuleKey("userAge");
        treeNode_11.setRuleDesc("用户年龄");

        // 链接：11->111
        TreeNodeLink treeNodeLink_111 = new TreeNodeLink();
        treeNodeLink_111.setNodeIdFrom(11L);
        treeNodeLink_111.setNodeIdTo(111L);
        treeNodeLink_111.setRuleLimitType(3);
        treeNodeLink_111.setRuleLimitValue("25");

        // 链接：11->112
        TreeNodeLink treeNodeLink_112 = new TreeNodeLink();
        treeNodeLink_112.setNodeIdFrom(11L);
        treeNodeLink_112.setNodeIdTo(112L);
        treeNodeLink_112.setRuleLimitType(5);
        treeNodeLink_112.setRuleLimitValue("25");

        List<TreeNodeLink> treeNodeLinkList_11 = new ArrayList<>();
        treeNodeLinkList_11.add(treeNodeLink_111);
        treeNodeLinkList_11.add(treeNodeLink_112);

        treeNode_11.setTreeNodeLinkList(treeNodeLinkList_11);

        // 节点：12
        TreeNode treeNode_12 = new TreeNode();
        treeNode_12.setTreeId(10001L);
        treeNode_12.setTreeNodeId(12L);
        treeNode_12.setNodeType(1);
        treeNode_12.setNodeValue(null);
        treeNode_12.setRuleKey("userAge");
        treeNode_12.setRuleDesc("用户年龄");

        // 链接：12->121
        TreeNodeLink treeNodeLink_121 = new TreeNodeLink();
        treeNodeLink_121.setNodeIdFrom(12L);
        treeNodeLink_121.setNodeIdTo(121L);
        treeNodeLink_121.setRuleLimitType(3);
        treeNodeLink_121.setRuleLimitValue("25");

        // 链接：12->122
        TreeNodeLink treeNodeLink_122 = new TreeNodeLink();
        treeNodeLink_122.setNodeIdFrom(12L);
        treeNodeLink_122.setNodeIdTo(122L);
        treeNodeLink_122.setRuleLimitType(5);
        treeNodeLink_122.setRuleLimitValue("25");

        List<TreeNodeLink> treeNodeLinkList_12 = new ArrayList<>();
        treeNodeLinkList_12.add(treeNodeLink_121);
        treeNodeLinkList_12.add(treeNodeLink_122);

        treeNode_12.setTreeNodeLinkList(treeNodeLinkList_12);

        // 节点：111
        TreeNode treeNode_111 = new TreeNode();
        treeNode_111.setTreeId(10001L);
        treeNode_111.setTreeNodeId(111L);
        treeNode_111.setNodeType(2);
        treeNode_111.setNodeValue("果实A");

        // 节点：112
        TreeNode treeNode_112 = new TreeNode();
        treeNode_112.setTreeId(10001L);
        treeNode_112.setTreeNodeId(112L);
        treeNode_112.setNodeType(2);
        treeNode_112.setNodeValue("果实B");

        // 节点：121
        TreeNode treeNode_121 = new TreeNode();
        treeNode_121.setTreeId(10001L);
        treeNode_121.setTreeNodeId(121L);
        treeNode_121.setNodeType(2);
        treeNode_121.setNodeValue("果实C");

        // 节点：122
        TreeNode treeNode_122 = new TreeNode();
        treeNode_122.setTreeId(10001L);
        treeNode_122.setTreeNodeId(122L);
        treeNode_122.setNodeType(2);
        treeNode_122.setNodeValue("果实D");

        // 树根
        TreeRoot treeRoot = new TreeRoot();
        treeRoot.setTreeId(10001L);
        treeRoot.setTreeRootNodeId(1L);
        treeRoot.setTreeName("规则决策树");

        Map<Long, TreeNode> treeNodeMap = new HashMap<>();
        treeNodeMap.put(1L, treeNode_01);
        treeNodeMap.put(11L, treeNode_11);
        treeNodeMap.put(12L, treeNode_12);
        treeNodeMap.put(111L, treeNode_111);
        treeNodeMap.put(112L, treeNode_112);
        treeNodeMap.put(121L, treeNode_121);
        treeNodeMap.put(122L, treeNode_122);

        treeRich = new TreeRich(treeRoot, treeNodeMap);
    }


    @Test
    @DisplayName("组合模式,决策树")
    void test_tree() {
        log.info("决策树组合结构信息:{}", treeRich);

        IEngine treeEngineHandle = new TreeNodeHandle();

        /**
         * 测试数据
         * 果实A：gender=man、age=22
         * 果实B：gender=man、age=29
         * 果实C：gender=woman、age=22
         * 果实D：gender=woman、age=29
         */
        Map<String, String> decisionMatter = new HashMap<>();
        decisionMatter.put("gender", "man");
        decisionMatter.put("age", "29");

        EngineResult result = treeEngineHandle.process(10001L, "老王", treeRich, decisionMatter);
        log.info("测试结果：{}", JSONUtil.toJsonStr(result));

    }

    @Test
    @DisplayName("装饰器模式")
    void loginSsoDecorator() {
        LoginSsoDecorator ssoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        String request = "1successhuahua";
        boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        log.info("登录校验:{},{}",request,success);

    }


}
