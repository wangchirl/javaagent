package com.shadow.core.javassist.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.javassist.handler.QuartzJobJavassistHandler;
import com.shadow.core.javassist.handler.SimpleJobJavassistHandler;
import com.shadow.core.javassist.handler.SpringJobJavassistHandler;
import com.shadow.core.javassist.handler.XxlJobJavassistHandler;
import com.shadow.utils.CommonConstants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class JavassistTransformer extends AbstractTransformer implements ClassFileTransformer {

    /**
     * 定时任务类型
     */
    private CommonConstants.ScheduleTypeEnum scheduleTypeEnum;

    public JavassistTransformer(Map<String, String> resolveArgs, CommonConstants.ScheduleTypeEnum scheduleTypeEnum) {
        super(resolveArgs);
        this.scheduleTypeEnum = scheduleTypeEnum;
    }

    /**
     * @param loader              加载当前 class 的类加载器
     * @param className           当前被加载类的 inner class name
     * @param classBeingRedefined 被 transform 过的 Class，一般情况下为 null
     * @param protectionDomain    被定义或重新定义的类的保护域
     * @param classfileBuffer     当前类的字节码信息
     */
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        // load assigned class
        if (className.equals(getInnerClassName())) {
            System.out.println("LOAD ASSIGNED CLASS : " + className);
            System.out.println("LOAD TIME AGENT ARGS : " + getArgs());
            switch (this.scheduleTypeEnum) {
                case XXL:
                    return new XxlJobJavassistHandler(getArgs()).handle(getClassName());
                case QUARTZ:
                    return new QuartzJobJavassistHandler(getArgs()).handle(getClassName());
                case SPRING:
                    return new SpringJobJavassistHandler(getArgs()).handle(getClassName());
                case SIMPLE:
                    return new SimpleJobJavassistHandler(getArgs()).handle(getClassName());
                default:
                    break;
            }
        }
        return null;
    }
}
