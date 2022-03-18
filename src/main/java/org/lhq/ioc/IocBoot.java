package org.lhq.ioc;

import lombok.extern.slf4j.Slf4j;
import org.lhq.anno.ioc.ComponentScan;

import java.util.Arrays;

@Slf4j
public class IocBoot {
    public static <T> void run(Class<T> clazz, String[] args){
      log.info("ioc容器启动");
      BeanFactory.beanScan("org.lhq.controller");
        ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
        String[] packageName = componentScan.value();
        Arrays.stream(packageName).forEach(log::info);
        BeanFactory.startContainer();
    }
}
