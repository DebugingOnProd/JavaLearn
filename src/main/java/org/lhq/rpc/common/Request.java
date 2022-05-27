package org.lhq.rpc.common;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Request {
    @Expose
    private String serviceName;
    @Expose
    private String method;
    @Expose
    private Map<String, String> headers = new HashMap<>();
    @Expose
    private Object[] parameters;
    @Expose
    private Class<?>[] parameterTypes;

}
