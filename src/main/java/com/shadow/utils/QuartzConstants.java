package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Type;

public class QuartzConstants {

    private QuartzConstants() {

    }
    // tips
    public static String QUARTZ_SUCCESS = "Execute Quartz Job Successful !";

    // ❀❀❀❀❀❀❀❀ Quartz 相关类及其描述符 ❀❀❀❀❀❀❀❀

    public static Type QUARTZ_JOB_DATA_MAP_TYPE = Type.getType("Lorg/quartz/JobDataMap;");
    public static Type QUARTZ_CRON_TRIGGER_IMPL_TYPE = Type.getType("Lorg/quartz/impl/triggers/CronTriggerImpl;");
    public static Type QUARTZ_JOB_KEY_TYPE = Type.getType("Lorg/quartz/JobKey;");
    public static Type QUARTZ_SCHEDULER_TYPE = Type.getType("Lorg/quartz/Scheduler;");
    public static Type QUARTZ_TRIGGER_TYPE = Type.getType("Lorg/quartz/Trigger;");

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lorg/quartz/Scheduler;
    public static String SCHEDULER_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + QUARTZ_SCHEDULER_TYPE.getDescriptor();

    // (Lorg/quartz/JobKey;Lorg/quartz/JobDataMap;)V
    public static String V_JOB_KEY_JOB_DATA_MAP = CommonConstants.LEFT_BRACKETS + QUARTZ_JOB_KEY_TYPE.getDescriptor() + QUARTZ_JOB_DATA_MAP_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Lorg/quartz/JobKey;)V
    public static String V_JOB_KEY = CommonConstants.LEFT_BRACKETS + QUARTZ_JOB_KEY_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // "()Lorg/quartz/JobKey;"
    public static String JOB_KEY_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + QUARTZ_JOB_KEY_TYPE.getDescriptor() ;


}
