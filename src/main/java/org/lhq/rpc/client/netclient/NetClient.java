package org.lhq.rpc.client.netclient;

import org.lhq.rpc.discovery.ServiceInfo;

public interface NetClient {
    byte[] sendRequest(byte[] data, ServiceInfo sinfo) throws Throwable;

}
