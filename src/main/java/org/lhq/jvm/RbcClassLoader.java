package org.lhq.jvm;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Objects;

public class RbcClassLoader extends URLClassLoader {

    public RbcClassLoader(URL[] urls) {
        super(urls);
    }


    public static ClassLoader getRbcClassLoader(String classPath) throws IOException {
        File file = new File(classPath);

        if (!file.exists() || !file.isDirectory()) {
            throw new IOException("Please check if your path is correct!");
        }

        //获取所有的该目录下所有的jar文件
        File[] jars = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(filePointer -> filePointer.getName().endsWith(".jar")).toArray(File[]::new);

        URL[] urls = new URL[jars.length];

        ////将其所有的路径转换为URL
        //for (int i = 0; i < urls.length; i++) {
        //    File var0 = jars[i].getCanonicalFile();
        //    urls[i] = ParseUtil.fileToEncodedURL(var0);
        //}

        return new RbcClassLoader(urls);
    }

}
