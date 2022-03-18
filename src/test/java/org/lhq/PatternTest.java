package org.lhq;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lhq.designPattern.Singleton;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2021-10-28 21:32
 */
@Slf4j
public class PatternTest {

	@Test
	void singleton(){
		for (int i = 0; i < 200; i++) {
			log.info("测试单例");
			new Thread(()->{
				Singleton singleton = Singleton.getSingleton();
				log.info("{}",singleton);
			}).start();
		}
	}
}
