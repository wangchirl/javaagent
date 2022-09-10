package com.shadow.utils;
import static com.shadow.utils.CommonConstants.*;
import static com.shadow.utils.BaseConstants.*;

public class SpringConstants {

    private SpringConstants() {

    }
    // TIPS
    public static String PROXY_LOG_TIPS = "Spring " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;
    // ♣♣♣♣♣♣♣♣ Spring 相关类及其描述符 ♣♣♣♣♣♣♣♣

    /**
     * class name
     */
    public static String SPRING_APPLICATION_CONTEXT_CLASS = "org.springframework.context.ApplicationContext";
    public static String SPRING_AUTOWIRED_CLASS = "org.springframework.beans.factory.annotation.Autowired";
    public static String SPRING_REQUEST_MAPPING_CLASS = "org.springframework.web.bind.annotation.RequestMapping";
    public static String SPRING_PATH_VARIABLE_CLASS = "org.springframework.web.bind.annotation.PathVariable";
    public static String SPRING_REQUEST_PARAM_CLASS = "org.springframework.web.bind.annotation.RequestParam";
    public static String SPRING_REQUEST_BODY_CLASS = "org.springframework.web.bind.annotation.RequestBody";
    public static String SPRING_SCHEDULEDMETHODRUNNABLE_CLASS = "org.springframework.scheduling.support.ScheduledMethodRunnable";
    public static String SPRING_TASK_CLASS = "org.springframework.scheduling.config.Task";
    public static String SPRING_SCHEDULEDTASK_CLASS = "org.springframework.scheduling.config.ScheduledTask";
    public static String SPRING_QUARTZ_SCHEDULERFACTORYBEAN_CLASS = "org.springframework.scheduling.quartz.SchedulerFactoryBean";
    public static String SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_CLASS = "org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor";

    /**
     * internal class name
     */
    public static String SPRING_APPLICATION_CONTEXT_INTERNAL_CLASS = SPRING_APPLICATION_CONTEXT_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_AUTOWIRED_INTERNAL_CLASS = SPRING_AUTOWIRED_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_REQUEST_MAPPING_INTERNAL_CLASS = SPRING_REQUEST_MAPPING_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_PATH_VARIABLE_INTERNAL_CLASS = SPRING_PATH_VARIABLE_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_REQUEST_PARAM_INTERNAL_CLASS = SPRING_REQUEST_PARAM_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_REQUEST_BODY_INTERNAL_CLASS = SPRING_REQUEST_BODY_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_SCHEDULEDMETHODRUNNABLE_INTERNAL_CLASS = SPRING_SCHEDULEDMETHODRUNNABLE_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_TASK_INTERNAL_CLASS = SPRING_TASK_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_SCHEDULEDTASK_INTERNAL_CLASS = SPRING_SCHEDULEDTASK_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_QUARTZ_SCHEDULERFACTORYBEAN_INTERNAL_CLASS = SPRING_QUARTZ_SCHEDULERFACTORYBEAN_CLASS.replaceAll(DOT, BIAS);
    public static String SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_INTERNAL_CLASS = SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_CLASS.replaceAll(DOT, BIAS);

    /**
     * class descriptor
     */
    // Lorg/springframework/context/ApplicationContext;
    public static String SPRING_APPLICATION_CONTEXT_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_APPLICATION_CONTEXT_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/beans/factory/annotation/Autowired;
    public static String SPRING_AUTOWIRED_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_AUTOWIRED_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/web/bind/annotation/RequestMapping;
    public static String SPRING_REQUEST_MAPPING_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_REQUEST_MAPPING_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/web/bind/annotation/PathVariable;
    public static String SPRING_PATH_VARIABLE_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_PATH_VARIABLE_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/web/bind/annotation/RequestParam;
    public static String SPRING_REQUEST_PARAM_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_REQUEST_PARAM_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/web/bind/annotation/RequestBody;
    public static String SPRING_REQUEST_BODY_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_REQUEST_BODY_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/scheduling/support/ScheduledMethodRunnable;
    public static String SPRING_SCHEDULEDMETHODRUNNABLE_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_SCHEDULEDMETHODRUNNABLE_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/scheduling/config/Task;
    public static String SPRING_TASK_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_TASK_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/scheduling/config/ScheduledTask;
    public static String SPRING_SCHEDULEDTASK_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_SCHEDULEDTASK_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/scheduling/quartz/SchedulerFactoryBean;
    public static String SPRING_QUARTZ_SCHEDULERFACTORYBEAN_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_QUARTZ_SCHEDULERFACTORYBEAN_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;
    public static String SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lorg/springframework/scheduling/config/Task;
    public static String TASK_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SPRING_TASK_DESCRIPTOR;
}
