package org.lhq.anno.impl;



import java.lang.reflect.Method;

public class SelectImpl {

    public <T> T getReturnType() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("org.lhq.jdbc.dao.UserDao");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
        }
		return null;
    }
}
