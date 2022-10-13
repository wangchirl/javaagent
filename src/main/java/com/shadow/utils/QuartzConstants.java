package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Type;

public class QuartzConstants {

    private QuartzConstants() {

    }

    // TIPS
    public static String PROXY_LOG_TIPS = "Quartz " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;

    // ❀❀❀❀❀❀❀❀ Quartz 相关类及其描述符 ❀❀❀❀❀❀❀❀

    public static Type QUARTZ_JOBDATAMAP_TYPE = Type.getType("Lorg/quartz/JobDataMap;");
    public static Type QUARTZ_CRONTRIGGERIMPL_TYPE = Type.getType("Lorg/quartz/impl/triggers/CronTriggerImpl;");
    public static Type QUARTZ_JOBKEY_TYPE = Type.getType("Lorg/quartz/JobKey;");
    public static Type QUARTZ_SCHEDULER_TYPE = Type.getType("Lorg/quartz/Scheduler;");
    public static Type QUARTZ_TRIGGER_TYPE = Type.getType("Lorg/quartz/Trigger;");

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lorg/quartz/Scheduler;
    public static String SCHEDULER_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + QUARTZ_SCHEDULER_TYPE.getDescriptor();

    // (Lorg/quartz/JobKey;Lorg/quartz/JobDataMap;)V
    public static String V_JOBKEY_JOBDATAMAP = CommonConstants.LEFT_BRACKETS + QUARTZ_JOBKEY_TYPE.getDescriptor() + QUARTZ_JOBDATAMAP_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Lorg/quartz/JobKey;)V
    public static String V_JOBKEY = CommonConstants.LEFT_BRACKETS + QUARTZ_JOBKEY_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // "()Lorg/quartz/JobKey;"
    public static String JOBKEY_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + QUARTZ_JOBKEY_TYPE.getDescriptor() ;


}
