package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Type;


public class SpringConstants {

    private SpringConstants() {

    }
    // tips
    public static String SPRING_SUCCESS = "Execute Spring Job Successful !";

    // ♣♣♣♣♣♣♣♣ Spring 相关类及其描述符 ♣♣♣♣♣♣♣♣

    public static Type SPRING_APPLICATION_CONTEXT_TYPE = Type.getType("Lorg/springframework/context/ApplicationContext;");
    public static Type SPRING_AUTOWIRED_TYPE = Type.getType("Lorg/springframework/beans/factory/annotation/Autowired;");
    public static Type SPRING_REQUEST_MAPPING_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/RequestMapping;");
    public static Type SPRING_PATH_VARIABLE_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/PathVariable;");
    public static Type SPRING_REQUEST_PARAM_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/RequestParam;");
    public static Type SPRING_REQUEST_BODY_TYPE = Type.getType("Lorg/springframework/web/bind/annotation/RequestBody;");
    public static Type SPRING_SCHEDULED_METHOD_RUNNABLE_TYPE = Type.getType("Lorg/springframework/scheduling/support/ScheduledMethodRunnable;");
    public static Type SPRING_TASK_TYPE = Type.getType("Lorg/springframework/scheduling/config/Task;");
    public static Type SPRING_SCHEDULED_TASK_TYPE = Type.getType("Lorg/springframework/scheduling/config/ScheduledTask;");
    public static Type SPRING_QUARTZ_SCHEDULER_FACTORY_BEAN_TYPE = Type.getType("Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;");
    public static Type SPRING_SCHEDULED_ANNOTATION_BEAN_POSTPROCESSOR_TYPE = Type.getType("Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;");
    public static Type SPRING_CRON_TRIGGER_TYPE = Type.getType("Lorg/springframework/scheduling/support/CronTrigger;");
    public static Type SPRING_ENVIRONMENT_TYPE = Type.getType("Lorg/springframework/core/env/Environment;");

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lorg/springframework/scheduling/config/Task;
    public static String TASK_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SPRING_TASK_TYPE.getDescriptor();

    // ()Lorg/springframework/core/env/Environment;
    public static String ENVIRONMENT_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SPRING_ENVIRONMENT_TYPE.getDescriptor();
}
