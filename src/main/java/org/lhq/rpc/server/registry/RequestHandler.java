package org.lhq.rpc.server.registry;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;
import org.lhq.rpc.common.Request;
import org.lhq.rpc.common.Response;
import org.lhq.rpc.common.Status;
import org.lhq.rpc.protocol.RpcProtocol;

import java.lang.reflect.Method;

@Slf4j
public class RequestHandler {
    private RpcProtocol protocol;

    private ServiceRegister serviceRegister;

    public RequestHandler(RpcProtocol protocol, ServiceRegister serviceRegister) {
        super();
        this.protocol = protocol;
        this.serviceRegister = serviceRegister;
    }

    public byte[] handleRequest(byte[] data) throws Exception {
        TimeInterval timer = DateUtil.timer();
        timer.start();
        // 1、解组消息
        Request req = this.protocol.unmarshallingRequest(data);
        String serviceName = req.getServiceName();
        String method = req.getMethod();
        Object[] parameters = req.getParameters();
        log.info("服务端收到请求:服务名{},请求方法:{},请求参数:{}",serviceName,method,parameters);
        // 2、查找服务对象
        ServiceObject so = this.serviceRegister.getServiceObject(req.getServiceName());

        Response rsp;

        if (so == null) {
            rsp = new Response(Status.NOT_FOUND);
        } else {
            // 3、反射调用对应的过程方法
            try {
                Method m = so.getInterf().getMethod(req.getMethod(), req.getParameterTypes());
                Object returnValue = m.invoke(so.getObj(), req.getParameters());
                rsp = new Response(Status.SUCCESS);
                rsp.setReturnValue(returnValue);
            } catch (Exception e) {
                rsp = new Response(Status.ERROR);
                rsp.setException(e);
            }
        }
        long timeConsuming = timer.intervalRestart();
        log.info("耗时:{}毫秒,结果为,{}",timeConsuming,rsp);
        // 4、编组响应消息
        return this.protocol.marshallingResponse(rsp);
    }
}
