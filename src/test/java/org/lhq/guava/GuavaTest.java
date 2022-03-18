package org.lhq.guava;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lhq.entity.User;

@Slf4j
class GuavaTest {
	@Test
	void optional() {
		User user = new User();
		user.setNickname("你爸爸");
		User or = Optional.fromNullable(user).or(new User());
		log.info("不为空的情况下{}",or);
		User sdada = Optional.<User>fromNullable(null).or(new User().setNickname("sdada"));
		log.info("为空的情况下{}",sdada);
	}
	@Test
	void aysnake(){
		Futures.addCallback(null, new FutureCallback<Object>() {
			@Override
			public void onSuccess(Object result) {
				log.info("成功输出");
			}

			@Override
			public void onFailure(Throwable t) {
				log.error("失败输出");
			}
		},null);
	}
}
