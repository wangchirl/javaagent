package com.jar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取 main class：
 * 传参 file:///F:\source\scheduler\target\shadow.jar
 */
public class JarRunner {

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }
        URL url = null;
        try {
            url = new URL(args[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JarClassLoader loader = new JarClassLoader(url);
        String name = null;
        try {
            name = loader.getMainClassName();
        } catch (IOException e) {
            fatal("error while loading JAR file");
            e.printStackTrace();
        }
        if (name == null) {
            fatal("Specified jar file does not contain a 'Main-Class' manifest attribute");
        }
        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, newArgs.length);

        try {
            loader.invokeClass(name, newArgs);
        } catch (ClassNotFoundException e) {
            fatal("Class Not Found");
        } catch (NoSuchMethodException e) {
            fatal("Class does not define a 'main' method : " + name);
        } catch (Exception e) {
            fatal(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void fatal(String info) {
        System.out.println(info);
        System.exit(1);
    }

    private static void usage() {
        fatal("Usage : java JarRunner url [args...]");
    }
}
