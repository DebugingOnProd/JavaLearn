package org.lhq.rpc;

import lombok.extern.slf4j.Slf4j;
import org.lhq.entity.User;
import org.lhq.rpc.client.ClientStubProxyFactory;
import org.lhq.rpc.client.netclient.NettyNetClient;
import org.lhq.rpc.discovery.ZookeeperServiceInfoDiscoverer;
import org.lhq.rpc.protocol.JsonRpcProtocol;
import org.lhq.rpc.protocol.RpcProtocol;
import org.lhq.service.UserService;
import org.lhq.utils.PropertiesUtils;

import java.util.HashMap;
@Slf4j
public class Consumer {
    public static void main(String[] args) {
        String protocol = PropertiesUtils.getProperties("rpc.protocol");
        ClientStubProxyFactory cspf = new ClientStubProxyFactory();
        // 设置服务发现者
        cspf.setServiceInfoDiscoverer(new ZookeeperServiceInfoDiscoverer());

        // 设置支持的协议
        HashMap<String, RpcProtocol> supportRpcProtocols = new HashMap<>();
        supportRpcProtocols.put(protocol, new JsonRpcProtocol());
        cspf.setSupportRpcProtocols(supportRpcProtocols);

        // 设置网络层实现
        cspf.setNetClient(new NettyNetClient());

        UserService demoService = cspf.getProxy(UserService.class); // 获取远程服务代理
        User user = demoService.getUser(); // 执行远程方法

        log.info("获取到的结果；{}" , user);
    }
}
