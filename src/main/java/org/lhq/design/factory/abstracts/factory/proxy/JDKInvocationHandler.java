package org.lhq.design.factory.abstracts.factory.proxy;

import org.lhq.design.factory.abstracts.factory.ICacheAdapter;
import org.lhq.utils.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {
	private ICacheAdapter cacheAdapter;

	public JDKInvocationHandler(ICacheAdapter cacheAdapter) {
		this.cacheAdapter = cacheAdapter;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return ICacheAdapter.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter, args);
	}
}
