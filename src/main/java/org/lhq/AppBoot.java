package org.lhq;

import lombok.extern.slf4j.Slf4j;
import org.lhq.anno.ioc.ComponentScan;
import org.lhq.web.HttpServer;

@Slf4j
@ComponentScan
public class AppBoot {
    public static void main(String[] args) {
		HttpServer.run(0,args);
    }
}
