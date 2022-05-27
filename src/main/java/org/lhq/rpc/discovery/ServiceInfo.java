package org.lhq.rpc.discovery;

import lombok.Data;

@Data
public class ServiceInfo {
    private String name;

    private String protocol;

    private String address;

}
