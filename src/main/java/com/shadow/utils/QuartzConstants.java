package com.shadow.utils;

import static com.shadow.utils.CommonConstants.*;
import static com.shadow.utils.BaseConstants.*;

public class QuartzConstants {

    private QuartzConstants() {

    }

    // TIPS
    public static String PROXY_LOG_TIPS = "Quartz " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;

    // ❀❀❀❀❀❀❀❀ Quartz 相关类及其描述符 ❀❀❀❀❀❀❀❀

    /**
     * class name
     */
    public static String QUARTZ_JOBDATAMAP_CLASS = "org.quartz.JobDataMap";
    public static String QUARTZ_CRONTRIGGERIMPL_CLASS = "org.quartz.impl.triggers.CronTriggerImpl";
    public static String QUARTZ_JOBKEY_CLASS = "org.quartz.JobKey";
    public static String QUARTZ_SCHEDULER_CLASS = "org.quartz.Scheduler";

    /**
     * internal class name
     */
    public static String QUARTZ_JOBDATAMAP_INTERNAL_CLASS = QUARTZ_JOBDATAMAP_CLASS.replaceAll(DOT, BIAS);
    public static String QUARTZ_CRONTRIGGERIMPL_INTERNAL_CLASS = QUARTZ_CRONTRIGGERIMPL_CLASS.replaceAll(DOT, BIAS);
    public static String QUARTZ_JOBKEY_INTERNAL_CLASS = QUARTZ_JOBKEY_CLASS.replaceAll(DOT, BIAS);
    public static String QUARTZ_SCHEDULER_INTERNAL_CLASS = QUARTZ_SCHEDULER_CLASS.replaceAll(DOT, BIAS);

    // Lorg/quartz/JobDataMap;
    public static String QUARTZ_JOBDATAMAP_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + QUARTZ_JOBDATAMAP_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/quartz/impl/triggers/CronTriggerImpl;
    public static String QUARTZ_CRONTRIGGERIMPL_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + QUARTZ_CRONTRIGGERIMPL_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/quartz/JobKey;
    public static String QUARTZ_JOBKEY_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + QUARTZ_JOBKEY_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/quartz/Scheduler;
    public static String QUARTZ_SCHEDULER_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + QUARTZ_SCHEDULER_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lorg/quartz/Scheduler;
    public static String SCHEDULER_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + QUARTZ_SCHEDULER_DESCRIPTOR;

    // (Lorg/quartz/JobKey;Lorg/quartz/JobDataMap;)V
    public static String V_JOBKEY_JOBDATAMAP = CommonConstants.LEFT_BRACKETS + QUARTZ_JOBKEY_DESCRIPTOR + QUARTZ_JOBDATAMAP_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Lorg/quartz/JobKey;)V
    public static String V_JOBKEY = CommonConstants.LEFT_BRACKETS + QUARTZ_JOBKEY_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

}
