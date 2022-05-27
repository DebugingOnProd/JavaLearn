package org.lhq.rpc.discovery;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.I0Itec.zkclient.ZkClient;
import org.lhq.utils.PropertiesUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ZookeeperServiceInfoDiscoverer implements ServiceInfoDiscoverer{
    ZkClient client;
    private String centerRootPath = "/wang-rpc";

    public ZookeeperServiceInfoDiscoverer() {
        String address = PropertiesUtils.getProperties("zk.address");
        client = new ZkClient(address);
        client.setZkSerializer(new DefaultZkSerializer());
    }


    @Override
    public List<ServiceInfo> getServiceInfo(String name) {
        String servicePath = centerRootPath + "/" + name + "/service";
        List<String> children = client.getChildren(servicePath);
        List<ServiceInfo> resources = new ArrayList<>();
        for (String ch : children) {
            String deCh = URLDecoder.decode(ch, StandardCharsets.UTF_8);
            ServiceInfo r = JSONUtil.toBean(deCh, ServiceInfo.class);
            resources.add(r);
        }
        return resources;
    }
}
