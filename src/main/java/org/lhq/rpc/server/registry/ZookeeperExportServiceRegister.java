package org.lhq.rpc.server.registry;

import cn.hutool.json.JSONUtil;
import org.I0Itec.zkclient.ZkClient;
import org.lhq.rpc.discovery.DefaultZkSerializer;
import org.lhq.rpc.discovery.ServiceInfo;
import org.lhq.utils.PropertiesUtils;

import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ZookeeperExportServiceRegister implements ServiceRegister{
    private ZkClient client;

    private String centerRootPath = "/wang-rpc";

    private Map<String, ServiceObject> serviceMap = new HashMap<>();


    public ServiceObject getServiceObject(String name) {
        return this.serviceMap.get(name);
    }

    public ZookeeperExportServiceRegister() {
        String addr = PropertiesUtils.getProperties("zk.address");
        client = new ZkClient(addr);
        client.setZkSerializer(new DefaultZkSerializer());
    }


    public void register(ServiceObject so, String protocolName, int port) throws Exception {
        if (so == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        this.serviceMap.put(so.getName(), so);

        ServiceInfo soInf = new ServiceInfo();

        String host = InetAddress.getLocalHost().getHostAddress();
        String address = host + ":" + port;
        soInf.setAddress(address);
        soInf.setName(so.getInterf().getName());
        soInf.setProtocol(protocolName);
        this.exportService(soInf);

    }

    private void exportService(ServiceInfo serviceResource) {
        String serviceName = serviceResource.getName();
        String uri = JSONUtil.toJsonStr(serviceResource);
        uri = URLEncoder.encode(uri, StandardCharsets.UTF_8);
        String servicePath = centerRootPath + "/" + serviceName + "/service";
        if (!client.exists(servicePath)) {
            client.createPersistent(servicePath, true);
        }
        String uriPath = servicePath + "/" + uri;
        if (client.exists(uriPath)) {
            client.delete(uriPath);
        }
        client.createEphemeral(uriPath);
    }
}
