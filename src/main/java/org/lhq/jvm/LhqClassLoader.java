package org.lhq.jvm;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Wallace
 */
public class LhqClassLoader extends URLClassLoader {


    public LhqClassLoader(URL[] urls) {
        super(urls);
    }

    public LhqClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void addJar(URL url) {
        this.addURL(url);
    }


}
