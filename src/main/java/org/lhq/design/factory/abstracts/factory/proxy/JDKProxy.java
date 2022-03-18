package org.lhq.design.factory.abstracts.factory.proxy;

import org.lhq.design.factory.abstracts.factory.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxy {
	public static <T> T getProxy(Class<T> interfaceClass, ICacheAdapter cacheAdapter) throws Exception {
		InvocationHandler handler = new JDKInvocationHandler(cacheAdapter);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?>[] classes = interfaceClass.getInterfaces();
		return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, handler);
	}
}
