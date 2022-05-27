package org.lhq.se.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private Object object;

    public JDKProxy(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object, args);
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), object.getClass().getInterfaces(),this);
    }
}
