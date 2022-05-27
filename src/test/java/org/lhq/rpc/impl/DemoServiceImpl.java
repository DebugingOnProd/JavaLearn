package org.lhq.rpc.impl;

import org.lhq.rpc.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String hello(String caller) {
        return "hello " + caller;
    }
}
