package org.lhq.rpc.protocol;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.lhq.rpc.common.Request;
import org.lhq.rpc.common.Response;

import java.nio.charset.StandardCharsets;

public class JsonRpcProtocol implements RpcProtocol{
    public byte[] marshallingRequest(Request req) {
        return JSONUtil.toJsonStr(req).getBytes(StandardCharsets.UTF_8);
    }

    public Request unmarshallingRequest(byte[] data) {
        String str = StrUtil.str(data, StandardCharsets.UTF_8);
        return JSONUtil.toBean(str, Request.class);
    }

    public byte[] marshallingResponse(Response rsp) {
        return JSONUtil.toJsonStr(rsp).getBytes(StandardCharsets.UTF_8);
    }

    public Response unmarshallingResponse(byte[] data) {
        String str = StrUtil.str(data, StandardCharsets.UTF_8);
        return JSONUtil.toBean(str, Response.class);
    }
}
