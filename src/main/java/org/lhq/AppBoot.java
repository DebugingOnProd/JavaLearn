package org.lhq;

import lombok.extern.slf4j.Slf4j;
import org.lhq.anno.ioc.ComponentScan;
import org.lhq.ioc.IocBoot;
import org.lhq.web.HttpServer;

@Slf4j
@ComponentScan({"org.lhq.controller","org.lhq.service"})
public class AppBoot {
    public static void main(String[] args) {
        IocBoot.run(AppBoot.class,args);
		HttpServer.run(0,args);
    }
}
