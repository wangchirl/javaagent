package com.shadow.core.asm.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.asm.handler.QuartzJobAsmHandler;
import com.shadow.core.asm.handler.SimpleJobAsmHandler;
import com.shadow.core.asm.handler.SpringJobAsmHandler;
import com.shadow.core.asm.handler.XxlJobAsmHandler;
import com.shadow.utils.Constants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

public class AsmTransformer extends AbstractTransformer implements ClassFileTransformer {

    /**
     * 定时任务类型
     */
    private Constants.ScheduleTypeEnum scheduleTypeEnum;

    public AsmTransformer(Map<String, String> resolveArgs, Constants.ScheduleTypeEnum scheduleTypeEnum) {
        super(resolveArgs);
        this.scheduleTypeEnum = scheduleTypeEnum;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals(getInnerClassName())) {
            switch (this.scheduleTypeEnum) {
                case XXL:
                    return new XxlJobAsmHandler(getInnerClassName(), getArgs()).handle(classfileBuffer);
                case QUARTZ:
                    return new QuartzJobAsmHandler(getInnerClassName(), getArgs()).handle(classfileBuffer);
                case SPRING:
                    return new SpringJobAsmHandler(getInnerClassName(), getArgs()).handle(classfileBuffer);
                case SIMPLE:
                    return new SimpleJobAsmHandler(getInnerClassName(), getArgs()).handle(classfileBuffer);
                default:
                    break;
            }
        }
        return null;
    }
}
