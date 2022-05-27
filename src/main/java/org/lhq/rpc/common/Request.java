package org.lhq.rpc.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Request {
    private String serviceName;

    private String method;

    private Map<String, String> headers = new HashMap<>();

    private Object[] parameters;

    private Class<?>[] parameterTypes;

}
