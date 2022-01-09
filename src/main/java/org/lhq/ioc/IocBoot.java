package org.lhq.ioc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IocBoot {
    public static void run(){
      log.info("ioc容器启动");
      BeanFactory.startContainer();
    }
}
