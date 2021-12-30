package org.lhq.jvm;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {

    public static Method initAddMethod() {
        try {
            Method add = LhqClassLoader.class.getDeclaredMethod("addJar", URL.class);
            add.setAccessible(true);
            return add;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Method addURL = null;
    public static URLClassLoader classloader = null;

    public static void main(String[] args) {

        /*
         *  系统加载jar
         */
        try {

            // 系统ClassLoader只能加载.jar
            // 动态加载jar
            addURL = initAddMethod();
            classloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
            String url = "file:"+ "C:\\Users\\Wallace\\Downloads\\mc\\server.jar"; // 包路径定义
            System.out.println(url);
            URL classUrl = new URL(url);
            addURL.invoke(classloader, classUrl);

            String className = "net.minecraft.server.MinecraftServer";
            Class<?> c = Class.forName(className);
            Object obj = c.getDeclaredConstructor().newInstance();
            // 被调用函数的参数
            Class[] parameterTypes = {};
            Method method2 = c.getDeclaredMethod("main",parameterTypes);
            method2.invoke(obj);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}


