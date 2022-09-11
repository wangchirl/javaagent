package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Type;


public class SpringConstants {

    private SpringConstants() {

    }

    // TIPS
    public static String PROXY_LOG_TIPS = "Spring " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;

    // ♣♣♣♣♣♣♣♣ Spring 相关类及其描述符 ♣♣♣♣♣♣♣♣

    public static Type SPRING_APPLICATION_CONTEXT_TYPE = Type.getType("Lorg/springframework/context/ApplicationContext;");
    public static Type SPRING_AUTOWIRED_TYPE = Type.getType("Lorg/springframework/beans/factory/annotation/Autowired;");
    public static Type SPRING_REQUEST_MAPPING_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/RequestMapping;");
    public static Type SPRING_PATH_VARIABLE_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/PathVariable;");
    public static Type SPRING_REQUEST_PARAM_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/RequestParam;");
    public static Type SPRING_REQUEST_BODY_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/RequestBody;");
    public static Type SPRING_SCHEDULEDMETHODRUNNABLE_TYPE = Type.getType("Lorg/springframework/scheduling/support/ScheduledMethodRunnable;");
    public static Type SPRING_TASK_TYPE = Type.getType("Lorg/springframework/scheduling/config/Task;");
    public static Type SPRING_SCHEDULEDTASK_TYPE = Type.getType("Lorg/springframework/scheduling/config/ScheduledTask;");
    public static Type SPRING_QUARTZ_SCHEDULERFACTORYBEAN_TYPE = Type.getType("Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;");
    public static Type SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_TYPE = Type.getType("Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;");
    public static Type SPRING_CRONTRIGGER_TYPE = Type.getType("Lorg/springframework/scheduling/support/CronTrigger;");
    public static Type SPRING_ENVIRONMENT_TYPE = Type.getType("Lorg/springframework/core/env/Environment;");

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lorg/springframework/scheduling/config/Task;
    public static String TASK_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SPRING_TASK_TYPE.getDescriptor();

    // ()Lorg/springframework/core/env/Environment;
    public static String ENVIRONMENT_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SPRING_ENVIRONMENT_TYPE.getDescriptor();
}
