package org.lhq.rpc.protocol;

import org.checkerframework.checker.units.qual.C;
import org.lhq.rpc.common.Request;
import org.lhq.rpc.common.Response;

public interface RpcProtocol {
    //编码请求
    byte[] marshallingRequest(Request req) throws Exception;

    //解码请求
    Request unmarshallingRequest(byte[] data) throws Exception;

    //编码响应
    <T> byte[] marshallingResponse(Response<T> rsp) throws Exception;

    //解码响应
     Response<?> unmarshallingResponse(byte[] data, Class<?> clazz) throws Exception;
}
