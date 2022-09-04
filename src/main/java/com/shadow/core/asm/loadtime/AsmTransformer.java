package com.shadow.core.asm.loadtime;

import com.shadow.utils.Constants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class AsmTransformer implements ClassFileTransformer {

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
     * 记录 javaagent 传递的参数信息
     */
    private Map<String, String> args;

    /**
     * 定时任务类型
     */
    private Constants.ScheduleTypeEnum scheduleTypeEnum;

    public AsmTransformer(Map<String, String> resolveArgs, Constants.ScheduleTypeEnum scheduleTypeEnum) {
        this.args = resolveArgs;
        this.scheduleTypeEnum = scheduleTypeEnum;
        this.className = this.args.get(Constants.CONTROLLER_CLASS);
        this.innerClassName = this.className.replaceAll(Constants.DOT, Constants.BIAS);
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(this.innerClassName)) {
            switch (this.scheduleTypeEnum) {
                case XXL:
                    return new XxlJobAsmHandler(innerClassName, this.args).handle(classfileBuffer);
                case QUARTZ:
                    return new QuartzJobAsmHandler(innerClassName, this.args).handle(classfileBuffer);
                case SPRING:
                    return new SpringJobAsmHandler(innerClassName, this.args).handle(classfileBuffer);
                case SIMPLE:
                    return new SimpleJobAsmHandler(innerClassName, this.args).handle(classfileBuffer);
                default:
                    break;
            }
        }
        return null;
    }
}
