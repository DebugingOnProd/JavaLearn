package org.lhq.designPattern;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2021-10-28 15:58
 */
@Slf4j
public class Singleton {
	//private static final Logger log = LoggerFactory.getLogger(Singleton.class);
	private volatile static Singleton singleton;
	private Singleton (){}
	public static Singleton getSingleton() {
		if (singleton == null) {
			log.info("第一道锁");
			synchronized (Singleton.class) {
				log.info("再次检查不为空");
				if (singleton == null) {
					log.info("创建对象");
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}

}
