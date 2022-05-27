package org.lhq.rpc.client;

import lombok.Getter;
import lombok.Setter;
import org.lhq.rpc.client.netclient.NetClient;
import org.lhq.rpc.client.netclient.NettyNetClient;
import org.lhq.rpc.discovery.ServiceInfoDiscoverer;
import org.lhq.rpc.discovery.ZookeeperServiceInfoDiscoverer;
import org.lhq.rpc.protocol.RpcProtocol;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class ClientStubProxyFactory {
    //服务发现
    private ServiceInfoDiscoverer serviceInfoDiscoverer = new ZookeeperServiceInfoDiscoverer();

    //代理对象缓存，避免每次都新建
    private Map<Class<?>, Object> objectCache = new HashMap<>();

    //通信客户端，用于发送请求
    private NetClient netClient = new NettyNetClient();

    private Map<String, RpcProtocol> supportRpcProtocols;

    public <T> T getProxy(Class<T> interf) {
        T obj = (T) this.objectCache.get(interf);
        if (obj == null) {
            obj = (T) Proxy.newProxyInstance(interf.getClassLoader(), new Class<?>[]{interf},
                    new ClientStubInvocationHandler(interf, serviceInfoDiscoverer, netClient));
            this.objectCache.put(interf, obj);
        }
        return obj;
    }

}
