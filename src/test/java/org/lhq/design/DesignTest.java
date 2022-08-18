package org.lhq.design;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lhq.design.adapter.SocketAdapter;
import org.lhq.design.adapter.Volt;
import org.lhq.design.adapter.impl.SocketAdapterImpl;
import org.lhq.design.agent.dao.UserDao;
import org.lhq.design.bridge.DriverManger;
import org.lhq.design.bridge.DriverMangerImpl;
import org.lhq.design.bridge.MySqlDriver;
import org.lhq.design.bridge.pay.channel.AliPay;
import org.lhq.design.bridge.pay.channel.WxPay;
import org.lhq.design.bridge.pay.mode.PayFace;
import org.lhq.design.bridge.pay.mode.PayFingerprint;
import org.lhq.design.builder.Builder;
import org.lhq.design.builder.ComputerBuilder;
import org.lhq.design.builder.Director;
import org.lhq.design.builder.entity.Computer;
import org.lhq.design.command.Command;
import org.lhq.design.command.Invoker;
import org.lhq.design.command.impl.CmdReceiver;
import org.lhq.design.command.impl.DeleteCommand;
import org.lhq.design.command.impl.MoveCommand;
import org.lhq.design.command.impl.PowerShellReceiver;
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
import org.lhq.design.corp.approval.AuthLink;
import org.lhq.design.corp.approval.AuthService;
import org.lhq.design.corp.approval.impl.AuthLinkSec;
import org.lhq.design.corp.approval.impl.AuthLinkThr;
import org.lhq.design.corp.approval.impl.AuthLinkTop;
import org.lhq.design.decorator.LoginSsoDecorator;
import org.lhq.design.decorator.SsoInterceptor;
import org.lhq.design.factory.*;
import org.lhq.design.filter.AndCriteria;
import org.lhq.design.filter.Criteria;
import org.lhq.design.filter.CriteriaFemale;
import org.lhq.design.filter.CriteriaMale;
import org.lhq.design.filter.CriteriaSingle;
import org.lhq.design.filter.Person;
import org.lhq.design.memento.CareTaker;
import org.lhq.design.memento.Originator;
import org.lhq.design.observer.BinaryObserver;
import org.lhq.design.observer.HexaObserver;
import org.lhq.design.observer.OctalObserver;
import org.lhq.design.observer.Subject;
import org.lhq.design.state.Context;
import org.lhq.design.state.StartState;
import org.lhq.design.state.StopState;
import org.lhq.design.strategy.SelectStrategy;
import org.lhq.design.strategy.impl.PoorGuyGrade;
import org.lhq.design.strategy.impl.SvipGrade;
import org.lhq.design.template.Basketball;
import org.lhq.design.template.Football;
import org.lhq.design.template.Game;
import org.lhq.design.visitor.ComputerPart;
import org.lhq.design.visitor.ComputerPartDisplayVisitor;
import org.lhq.entity.enums.DataSource;
import org.lhq.entity.enums.DbEnum;
import org.lhq.entity.enums.FactoryEnum;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
class DesignTest {

    /**
     * 定义一个创建对象的接口,让调用者根据条件决定调用哪个实现类,返回实现类对象
     */
    @Test
    @DisplayName("测试工厂模式")
    void testFactory() {
        DBDriverFactory factory = new DBDriverFactory();
        DBDriver driverMySql = factory.getDbDriver(DbEnum.MySql);
        driverMySql.getConnection();
        DBDriver driverOracle = factory.getDbDriver(DbEnum.Oracle);
        driverOracle.getConnection();
        DBDriver driverPostgreSql = factory.getDbDriver(DbEnum.PostgreSql);
        driverPostgreSql.getConnection();
    }

    /**
     * 当有多个工厂需要管理的时候需要一个抽象工厂来统一管理工厂
     * @throws SQLException
     */

    @Test
    @DisplayName("抽象工厂模式")
    void absFactory() throws SQLException {
        IDataSource db = FactoryProducer.getFactory(FactoryEnum.DataSource).getDb(DataSource.SameCity);
        DBDriver dbDriver = FactoryProducer.getFactory(FactoryEnum.DbType).getDbDriver(DbEnum.MySql);
        log.info("获取同城Mysql数据库链接,{},{}", db.getConnection(), dbDriver);
    }

    @Test
    @DisplayName("建造者模式")
    void builder() {
        log.info("装机模拟器");
        Builder builder = new ComputerBuilder();
        Director director = new Director();
        director.construct(builder);
        director.constructGamingPC();
        Computer computer = builder.build();
        log.info("我组装的游戏电脑:{}", computer);
        director.constructOfficePC();

    }

    @Test
    @DisplayName("适配器模式")
    void adapter() {
        SocketAdapter socketAdapter = new SocketAdapterImpl();
        Volt volt3 = getVolt(socketAdapter, 3);
        Volt volt12 = getVolt(socketAdapter, 12);
        Volt volt220 = getVolt(socketAdapter, 220);
        log.info("12V电源 {}",volt12.getVolts());
        log.info("3V电源 {}",volt3.getVolts());
        log.info("220V电源 {}",volt220.getVolts());
    }

    private Volt getVolt(SocketAdapter socketAdapter, int volt) {
        return switch (volt) {
            case 3 -> socketAdapter.get3Volt();
            case 12 -> socketAdapter.get12Volt();
            default -> socketAdapter.get220Volt();
        };

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
        log.info("登录校验:{},{}", request, success);

    }


    @Test
    void authLink() throws ParseException {

        AuthLink authLink = new AuthLinkTop("1000013", "王工")
                .appendNext(new AuthLinkSec("1000012", "张经理")
                        .appendNext(new AuthLinkThr("1000011", "段总")));

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = f.parse("2020-06-18 23:49:46");

        log.info("测试结果：{}", JSONUtil.toJsonStr(authLink.doAuth("老王", "1000998004813441", currentDate)));

        // 模拟三级负责人审批
        AuthService.auth("1000013", "1000998004813441");
        log.info("测试结果：{}", "模拟三级负责人审批，王工");
        log.info("测试结果：{}", JSONUtil.toJsonStr(authLink.doAuth("老王", "1000998004813441", currentDate)));

        // 模拟二级负责人审批
        AuthService.auth("1000012", "1000998004813441");
        log.info("测试结果：{}", "模拟二级负责人审批，张经理");
        log.info("测试结果：{}", JSONUtil.toJsonStr(authLink.doAuth("老王", "1000998004813441", currentDate)));

        // 模拟一级负责人审批
        AuthService.auth("1000011", "1000998004813441");
        log.info("测试结果：{}", "模拟一级负责人审批，段总");
        log.info("测试结果：{}", JSONUtil.toJsonStr(authLink.doAuth("小傅哥", "1000998004813441", currentDate)));


    }

    @Test
    @DisplayName("命令模式")
    void command() {
        Command deleteCommand = new DeleteCommand(new CmdReceiver(), "删除命令");
        Command deleteCommandPowerShell = new DeleteCommand(new PowerShellReceiver(), "删除命令");
        Command moveCommand = new MoveCommand(new PowerShellReceiver(), "移动命令");

        Invoker invoker = new Invoker();
        invoker.takeOrder(deleteCommand);
        invoker.takeOrder(moveCommand);
        invoker.takeOrder(deleteCommandPowerShell);

        invoker.placeOrders();


    }

    @Test
    @DisplayName("备忘录模式")
    void memento() {
        /**
         * 发起人（Originator）角色：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，它可以访问备忘录里的所有信息。
         * 备忘录（Memento）角色：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
         * 管理者（Caretaker）角色：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改。
         */

        //创建状态发起者
        Originator originator = new Originator();
        //创建状态管理者
        CareTaker careTaker = new CareTaker();

        originator.setHistory("状态 #1");
        originator.setHistory("状态 #2");
        //把状态保存到
        careTaker.add(originator.saveStateToMemento());
        originator.setHistory("状态 #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setHistory("状态 #4");

        log.info("当前状态 State: " + originator.getHistory());
        originator.getStateFromMemento(careTaker.get(0));
        log.info("First saved State: " + originator.getHistory());
        originator.getStateFromMemento(careTaker.get(1));
        log.info("Second saved State: " + originator.getHistory());
    }

    @Test
    @DisplayName("模板模式")
    void template() {
        Game game = new Football();
        game.play();
        System.out.println();
        game = new Basketball();
        game.play();
    }

    @Test
    @DisplayName("过滤器模式")
    void filter() {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));


        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);


        log.info("male:{}", male.meetCriteria(persons));
    }

    @Test
    @DisplayName("状态模式")
    void state() {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        log.info(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        log.info(context.getState().toString());
    }

    /**
     * 策略模式
     * 让调用者去决定调用哪个算法,从而避免服务方产生大量if else 语句
     * 好处是算法可以自由切换,避免过多判断语句,具有良好的拓展性
     * 缺点是所有的算法类都需要对外暴露
     */

    @Test
    @DisplayName("策略模式")
    void strategy(){
        SelectStrategy poorGuy = new SelectStrategy(new PoorGuyGrade());
        BigDecimal poorGuyPrice = poorGuy.executeCal(new BigDecimal("100"));
        log.info("穷鬼价格是:{}元",poorGuyPrice.toString());

        SelectStrategy svip = new SelectStrategy(new SvipGrade());
        BigDecimal svipPrice = svip.executeCal(new BigDecimal("100"));
        log.info("土豪的价格是:{}元",svipPrice.toString());

    }
    @Test
    @DisplayName("访问者模式")
    void visitor(){
        ComputerPart computerPart = new org.lhq.design.visitor.impl.Computer();
        computerPart.accept(new ComputerPartDisplayVisitor());
    }

}
