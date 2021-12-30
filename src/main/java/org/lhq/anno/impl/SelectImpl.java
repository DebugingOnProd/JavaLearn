package org.lhq.anno.impl;

import cn.hutool.core.util.ReflectUtil;
import org.lhq.anno.Select;

import java.lang.reflect.Method;

public class SelectImpl {

    public <T> T getReturnType(){
        Class<?> clazz = Class.forName("org.lhq.jdbc.dao.UserDao");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
        }

    }
}
