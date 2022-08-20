package com.shadow.core;

import com.shadow.utils.Constants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

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
     * 定时任务类型
     */
    private Constants.ScheduleTypeEnum scheduleTypeEnum;

    public JavassistTransformer(String className, Constants.ScheduleTypeEnum scheduleTypeEnum) {
        this.className = className;
        this.innerClassName = this.className.replaceAll(Constants.DOT, Constants.BIAS);
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
        if (className.equals(this.innerClassName)) {
            System.out.println("LOAD ASSIGNED CLASS : " + className);
            System.out.println("LOAD CLASS LOADER : " + loader);
            switch (this.scheduleTypeEnum) {
                case XXL:
                    return new XxlJobHandler().handle(loader, this.className);
                case QUARTZ:
                    return new QuartzJobHandler().handle(loader, this.className);
                case SPRING:
                    return new SpringJobHandler().handle(loader, this.className);
                case SIMPLE:
                    return new SimpleJobHandler().handle(loader, this.className);
                default:
                    break;
            }
        }
        return null;
    }
}
