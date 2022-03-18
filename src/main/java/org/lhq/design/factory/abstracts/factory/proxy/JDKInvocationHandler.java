package org.lhq.design.factory.abstracts.factory.proxy;

import cn.hutool.core.util.ClassLoaderUtil;
import org.lhq.design.factory.abstracts.factory.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {
	private ICacheAdapter cacheAdapter;

	public JDKInvocationHandler(ICacheAdapter cacheAdapter) {
		this.cacheAdapter = cacheAdapter;
	}

	public Object invoke(Object proxy, Method method, Object[] args) {
		return null;
	}
}
