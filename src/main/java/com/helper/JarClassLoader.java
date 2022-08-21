package com.helper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.*;
import java.util.jar.Attributes;

public class JarClassLoader extends URLClassLoader {

    private URL url;

    public JarClassLoader(URL url) {
        super(new URL[]{url});
        this.url = url;
    }

    public String getMainClassName() throws IOException {
        URL u = new URL("jar", "", this.url + "!/");
        System.out.println("Test URL => " + u);
        JarURLConnection urlConnection = (JarURLConnection) u.openConnection();
        Attributes mainAttributes = urlConnection.getMainAttributes();
        return mainAttributes != null ? mainAttributes.getValue(Attributes.Name.MAIN_CLASS) : null;
    }

    public void invokeClass(String name, String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = loadClass(name);
        Method main = aClass.getMethod("main", new Class[]{args.getClass()});
        main.setAccessible(true);
        int modifiers = main.getModifiers();
        if(main.getReturnType() != Void.class || !Modifier.isStatic(modifiers) ||
                !Modifier.isPublic(modifiers)) {
            throw new NoSuchMethodException("main");
        }
        main.invoke(null, new Object[]{args});
    }
}
