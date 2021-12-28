package org.lhq.jvm;


import cn.hutool.core.lang.JarClassLoader;
import cn.hutool.core.util.ClassLoaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class ClassLoaderTest {
    @Test
    public void loadJar() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File file = new File("C:\\Users\\Hades\\IdeaProjects\\WeedingDiaryBackEnd\\target\\WeedingDiaryBackEnd-1.0.0.jar");
        JarClassLoader jarClassLoader = ClassLoaderUtil.getJarClassLoader(file);
        Class<?> aClass = jarClassLoader.loadClass("org.pttd.WeedingApplication");
        Object o = aClass.newInstance();
        log.info(String.valueOf(aClass));
    }
    @Test
    public void loadJar2() throws MalformedURLException {
        File file = new File("C:\\Users\\Hades\\IdeaProjects\\WeedingDiaryBackEnd\\target\\WeedingDiaryBackEnd-1.0.0.jar");
        URL url = file.toURI().toURL();
    }


}
