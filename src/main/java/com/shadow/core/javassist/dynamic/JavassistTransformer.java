package com.shadow.core.javassist.dynamic;

import com.shadow.core.AbstractTransformer;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.FileUtils;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import javassist.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class JavassistTransformer extends AbstractTransformer implements ClassFileTransformer {

    public JavassistTransformer(Map<String, String> args) {
        super(args);
    }

    @Override
    protected boolean handlerMatched(Class<?> handler) {
        return getJobType().name().equalsIgnoreCase(handler.getSimpleName().replace(CommonConstants.JAVASSIST_HANDLER_NAME_SUFFIX, ""));
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(getInnerClassName())) {
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
                CtMethod method = cc.getDeclaredMethod(getArgs().get(CommonConstants.METHOD_NAME), new CtClass[]{string, string, object});
                // 5、重新设置方法体
                method.setBody(getArgs().get(CommonConstants.METHOD_BODY));
                byte[] bytes = cc.toBytecode();
                // 6、write file for debug
                if (isDebug()) {
                    FileUtils.writeBytes(getInnerClassName() + CommonConstants.CLASS_SUFFIX, bytes);
                }
                // 7、return new byte code
                return bytes;
            } catch (Exception e) {
                System.out.println("Javassist handle attach " + this.getClass().getSimpleName() + " Job Agent error " + e.getMessage());
                e.printStackTrace();
            }
        }
        // return origin bytes
        return classfileBuffer;
    }
}
