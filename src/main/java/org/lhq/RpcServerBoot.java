package org.lhq;

import org.lhq.rpc.protocol.JsonRpcProtocol;
import org.lhq.rpc.server.NettyRpcServer;
import org.lhq.rpc.server.registry.RequestHandler;
import org.lhq.rpc.server.registry.ServiceObject;
import org.lhq.rpc.server.registry.ServiceRegister;
import org.lhq.rpc.server.registry.ZookeeperExportServiceRegister;
import org.lhq.service.UserService;
import org.lhq.service.impl.UserServiceImpl;
import org.lhq.utils.PropertiesUtils;

public class RpcServerBoot {
    public static void main(String[] args) throws Exception {
        String protocol = PropertiesUtils.getProperties("rpc.protocol");
        // 服务注册
        ServiceRegister sr = new ZookeeperExportServiceRegister();
        UserService ds = new UserServiceImpl();
        ServiceObject so = new ServiceObject(UserService.class.getName(), UserService.class, ds);
        sr.register(so, protocol, 19000);

        RequestHandler reqHandler = new RequestHandler(new JsonRpcProtocol(), sr);

        NettyRpcServer server = new NettyRpcServer(19000, protocol, reqHandler);
        server.start();
        //server.stop();
    }
}
