package org.lhq.rpc.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class Response {
    private Status status;

    private Map<String, String> headers = new   HashMap<>();

    private Object returnValue;

    private Exception exception;

    public Response() {
    }

    public Response(Status status) {
        this.status = status;
    }
}

