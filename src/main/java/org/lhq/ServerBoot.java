package org.lhq;

import lombok.extern.slf4j.Slf4j;
import org.lhq.ioc.IocBoot;
import org.lhq.web.WebBoot;

@Slf4j
public class ServerBoot {
    public static void main(String[] args) {
        log.info("服务启动");
        IocBoot.run();
        //WebBoot.run();
    }
}
