package org.lhq.rpc;

import org.lhq.rpc.impl.DemoServiceImpl;
import org.lhq.rpc.protocol.JsonRpcProtocol;
import org.lhq.rpc.server.NettyRpcServer;
import org.lhq.rpc.server.registry.RequestHandler;
import org.lhq.rpc.server.registry.ServiceObject;
import org.lhq.rpc.server.registry.ServiceRegister;
import org.lhq.rpc.server.registry.ZookeeperExportServiceRegister;
import org.lhq.utils.PropertiesUtils;

public class Provider {
    public static void main(String[] args) throws Exception {

        String protocol = PropertiesUtils.getProperties("rpc.protocol");

        // 服务注册
        ServiceRegister sr = new ZookeeperExportServiceRegister();
        DemoService ds = new DemoServiceImpl();
        ServiceObject so = new ServiceObject(DemoService.class.getName(), DemoService.class, ds);
        sr.register(so, protocol, 19000);

        RequestHandler reqHandler = new RequestHandler(new JsonRpcProtocol(), sr);

        NettyRpcServer server = new NettyRpcServer(19000, protocol, reqHandler);
        server.start();
        System.in.read(); // 按任意键退出
        server.stop();
    }
}
