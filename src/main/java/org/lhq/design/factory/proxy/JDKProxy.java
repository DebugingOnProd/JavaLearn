package org.lhq.design.factory.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: JavaLearn
 * @description: jdk动态代理
 * @author: wang.defa
 * @create: 2022-03-24 19:48
 */
@Slf4j
public class JDKProxy<T> implements InvocationHandler {
	private T traget;

	public JDKProxy(T traget){
		this.traget = traget;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invoke = method.invoke(traget, args);
		return invoke;
	}
}
