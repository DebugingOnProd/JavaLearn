package org.lhq;

import io.vavr.Function7;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
class VavrTest {
	@Test
	void test(){
		Function7<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> func =  (o, o2, o3, o4, o5, o6, o7) -> o+o2;
		log.info(String.valueOf(func.apply(1,2,3,4,5,6,7)));
	}
}
