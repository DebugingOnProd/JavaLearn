package org.lhq.rpc.client;

import org.lhq.rpc.client.netclient.NetClient;
import org.lhq.rpc.common.Request;
import org.lhq.rpc.common.Response;
import org.lhq.rpc.discovery.ServiceInfo;
import org.lhq.rpc.discovery.ServiceInfoDiscoverer;
import org.lhq.rpc.protocol.JsonRpcProtocol;
import org.lhq.rpc.protocol.RpcProtocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class ClientStubInvocationHandler implements InvocationHandler {
    private Class<?> interf;

    private ServiceInfoDiscoverer serviceInfoDiscoverer;

    private NetClient netClient;

    private Random random = new Random();


    public <T> ClientStubInvocationHandler(Class<T> interf, ServiceInfoDiscoverer serviceInfoDiscoverer, NetClient netClient) {
        this.interf = interf;
        this.serviceInfoDiscoverer = serviceInfoDiscoverer;
        this.netClient = netClient;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) {
            return proxy.getClass().toString();
        }

        if (method.getName().equals("hashCode")) {
            return 0;
        }
        //根据名称去注册中心找到对应的服务
        String serviceName = this.interf.getName();
        List<ServiceInfo> serviceInfos = serviceInfoDiscoverer.getServiceInfo(serviceName);
        //缘份负载均衡
        ServiceInfo serviceInfo = serviceInfos.get(random.nextInt(serviceInfos.size()));

        // 2、构造request对象
        Request req = new Request();
        req.setServiceName(serviceInfo.getName());
        req.setMethod(method.getName());
        req.setParameters(args);
        req.setParameterTypes(method.getParameterTypes());

        // 3、协议
        RpcProtocol protocol = new JsonRpcProtocol();
        // 编组请求
        byte[] data = protocol.marshallingRequest(req);

        // 4、调用网络层发送请求
        byte[] repData = netClient.sendRequest(data, serviceInfo);

        // 5解组响应消息
        Response rsp = protocol.unmarshallingResponse(repData);

        // 6、结果处理
        if (rsp.getException() != null) {
            throw rsp.getException();
        }

        return rsp.getReturnValue();
    }
}
