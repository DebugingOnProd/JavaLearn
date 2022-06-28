package org.lhq.rpc.common;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Map;

@Data
public class Request {
    private String serviceName;
    private String method;
    private Map<String, String> headers = new HashMap<>();
    private Object[] parameters;
    private Class<?>[] parameterTypes;
    private Class<T> returnType;

}
