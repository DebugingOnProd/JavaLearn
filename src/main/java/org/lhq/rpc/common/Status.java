package org.lhq.rpc.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  Status {
    SUCCESS(200, "SUCCESS"), ERROR(500, "ERROR"), NOT_FOUND(404, "NOT FOUND");
    private int code;
    private String message;
}
