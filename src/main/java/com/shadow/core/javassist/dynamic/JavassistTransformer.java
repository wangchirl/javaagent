package com.shadow.core.javassist.dynamic;

import com.shadow.utils.Constants;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import javassist.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class JavassistTransformer implements ClassFileTransformer {

    /**
     * class name
     * {@code java.lang.String}
     */
    private String className;

    /**
     * inner class name
     * {@code java/lang/String}
     */
    private String innerClassName;

    /**
     * 代理参数
     */
    private Map<String, String> args;

    public JavassistTransformer(Map<String, String> args) {
        this.args = args;
        this.className = this.args.get(Constants.CONTROLLER_CLASS);
        this.innerClassName = this.className.replaceAll(Constants.DOT, Constants.BIAS);
    }

    /**
     * 是否 debug 模式
     */
    private boolean isDebug() {
        return Boolean.parseBoolean(this.args.get(Constants.DEBUG));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(innerClassName)) {
            try {
                ByteSequence byteSequence = new ByteSequence(classfileBuffer);
                // 1、得到类池
                ClassPool cp = new ClassPool();
                // 2、设置类路径
                cp.insertClassPath(new ClassClassPath(this.getClass()));
                // 3、得到需要操作的类
                CtClass cc = cp.makeClass(byteSequence);
                // 4、得到方法
                CtClass string = cp.get(String.class.getName());
                CtClass object = cp.get(Object.class.getName());
                CtMethod method = cc.getDeclaredMethod(args.get(Constants.METHOD_NAME), new CtClass[]{string, string, object});
                // 5、重新设置方法体
                method.setBody(args.get(Constants.METHOD_BODY));
                byte[] bytes = cc.toBytecode();
                // 6、write file for debug
                if (isDebug()) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bytes.length);
                    outputStream.write(bytes);
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(this.className + ".class"));
                    outputStream.writeTo(fileOutputStream);
                }
                // 7、return new byte code
                return bytes;
            } catch (Exception e) {
                System.out.println("handle attach " + this.getClass().getSimpleName() + " Job Agent error " + e.getMessage());
                e.printStackTrace();
            }
        }
        // return origin bytes
        return classfileBuffer;
    }
}
