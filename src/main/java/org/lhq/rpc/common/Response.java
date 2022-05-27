package org.lhq.rpc.common;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class Response {
    @Expose
    private Status status;
    @Expose
    private Map<String, String> headers = new   HashMap<>();
    @Expose
    private Object returnValue;
    @Expose
    private Exception exception;

    public Response() {
    }

    public Response(Status status) {
        this.status = status;
    }
}

