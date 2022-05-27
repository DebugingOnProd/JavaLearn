package org.lhq.rpc.protocol;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.lhq.rpc.common.Request;
import org.lhq.rpc.common.Response;
import org.lhq.utils.GsonUtils;

import java.nio.charset.StandardCharsets;

public class JsonRpcProtocol implements RpcProtocol{
    public byte[] marshallingRequest(Request req) {
        return GsonUtils.object2String(req).getBytes(StandardCharsets.UTF_8);
    }

    public Request unmarshallingRequest(byte[] data) {
        String str = StrUtil.str(data, StandardCharsets.UTF_8);
        return GsonUtils.stringToBean(str,Request.class);
    }

    public byte[] marshallingResponse(Response rsp) {
        return JSONUtil.toJsonStr(rsp).getBytes(StandardCharsets.UTF_8);
    }

    public Response unmarshallingResponse(byte[] data) {
        String str = StrUtil.str(data, StandardCharsets.UTF_8);
        return GsonUtils.stringToBean(str,Response.class);
    }
}
