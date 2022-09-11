package com.shadow.utils;

import com.shadow.core.AbstractHandler;
import jdk.internal.org.objectweb.asm.Opcodes;

public class CommonConstants {

    private CommonConstants() {

    }


    // tips
    public static String XXL_SUCCESS = "Execute Xxl Job Successful !";
    public static String QUARTZ_SUCCESS = "Execute Quartz Job Successful !";
    public static String SPRING_SUCCESS = "Execute Spring Job Successful !";
    public static final String TIPS = "Job agent ...";

    /**
     * 常见符号
     */
    public static String EQUAL = "=";
    public static String COMM = ",";
    public static String DOT = "\\.";
    public static String BIAS = "/";
    public static String AND = "&";
    public static String OR = "|";
    public static String SPACE = " ";
    public static String SEMICOLON = ";";
    public static String LEFT_BRACKETS = "(";
    public static String RIGHT_BRACKETS = ")";
    public static String DOLLER_S = "$";
    public static String DOLLER_D = "$$";

    /**
     * 默认方法路径前缀
     */
    public static String DEFAULT_HTTP_PATH_PREFIX = "/agent/run";

    public static String DEFAULT_CRUD_HTTP_PATH = "/agent/crud/{operation}/{taskKey}";

    /**
     * 路径后缀
     */
    public static String PATH_SUFFIX = "/{taskKey}";

    public enum ProxyTypeEnum {
        JAVASSIST,
        ASM,
        BUDDY
    }

    public static ProxyTypeEnum getByProxyTypeName(String type) {
        for (ProxyTypeEnum proxyTypeEnum : ProxyTypeEnum.values()) {
            if (proxyTypeEnum.name().equalsIgnoreCase(type)) {
                return proxyTypeEnum;
            }
        }
        return ProxyTypeEnum.JAVASSIST;
    }

    /**
     * 定时任务类型枚举
     */
    public enum ScheduleTypeEnum {
        XXL,
        QUARTZ,
        SPRING,
        SIMPLE
    }

    public static ScheduleTypeEnum getByJobTypeName(String type) {
        for (ScheduleTypeEnum scheduleTypeEnum : ScheduleTypeEnum.values()) {
            if (scheduleTypeEnum.name().equalsIgnoreCase(type)) {
                return scheduleTypeEnum;
            }
        }
        return null;
    }

    public enum JavaTypeEnum {
        Z, // boolean
        B, // byte
        C, // char
        S, // short
        I, // int
        J, // long
        F, // float
        D, // double
        V // void
    }

    /**
     * 字节码操作类型 javassist 、 ASM 、 ByteBuddy
     */
    public static String PROXY_TYPE = "proxyType";

    /**
     * JOB 类型 - 必须
     */
    public static String JOB_TYPE = "jobType";

    /**
     * 指定的 controller 类 - 必须
     */
    public static String CONTROLLER_CLASS = "ctlClass";

    /**
     * 注入 controller IOC 容器字段名称 - 非必须
     * {@link CommonConstants#DEFAULT_IOC_FIELD_VALUE}
     */
    public static String IOC_FIELD_NAME = "iocFieldName";

    /**
     * 默认注入 controller 的 ioc 容器字段名称
     */
    public static String DEFAULT_IOC_FIELD_VALUE = "$$$springIoc$$$";

    /**
     * ThreadLocal 传参使用的类 - 非必须
     */
    public static String THREADLOCAL_CLASS_NAME = "tlClass";

    /**
     * ThreadLocal 所在类的字段名称 - THREADLOCAL_CLASS 存在的情况下必须
     */
    public static String THREADLOCAL_FIELD_NAME = "tlFieldName";

    /**
     * 方法名称 - 非必须
     * default {@link AbstractHandler#getMethodName()}
     */
    public static String METHOD_NAME = "methodName";

    /**
     * 允许传入方法 body - 非必须
     */
    public static String METHOD_BODY = "methodBody";

    /**
     * HTTP 请求 uri - 非必须
     * {@link CommonConstants#DEFAULT_HTTP_PATH_PREFIX}
     */
    public static String HTTP_REQUEST_PREFIX_URI = "httpUri";

    /**
     * 是否输出代理后的 class file
     */
    public static String DEBUG = "debug";

    /**
     * System.out.println() log 是否打印
     */
    public static String LOGGER_PRINT_OPEN = "logger";


    // Simple Job CRUD 相关

    /**
     * Simple Job 定时任务容器对象字段名称
     */
    public static String SIMPLE_JOB_IOC_FIELD_NAME = "crudFieldName";

    /**
     * Simple Job IOC 对象类型
     */
    public static String SIMPLE_JOB_IOC_FIELD = "private com.shadow.supports.framework.CommonSchedulingConfigurer ";

    /**
     * 默认 Simple Job 定时任务容器对象字段名称
     */
    public static String DEFAULT_SIMPLE_JOB_IOC_FIELD_NAME = "$$$simpleJobConfigurer$$$";

    /**
     * 是否给 Simple Job 添加 CRUD 方法
     */
    public static String TASK_CRUD = "crud";

    /**
     * crud 方法名称
     */
    public static String DEFAULT_CRUD_METHOD_NAME = "$$$simpleJobCrud$$$";

    // ASM version
    public static int ASM_API_VERSION = Opcodes.ASM5;

    // 访问修饰符
    public static String ACC_PUBLIC = "public";
    public static String ACC_PROTECTED = "protected";
    public static String ACC_PRIVATE = "private";

    // 常量字段值
    public static String SPRING_REQUEST_PARAM_NAEM = "name";
    public static String SPRING_REQUEST_MAPPING_PATH = "path";
    public static String SPRING_REQUEST_MAPPING_VALUE = "value";
    public static String SPRING_REQUEST_PARAM_REQUIRED = "required";
    public static String SPRING_REQUEST_PARAM_NAME = "params";
    public static String SPRING_REQUEST_BODY_NAME = "body";
    public static String SPRING_PATH_VARIABLE_PARAMETER_NAME_OPERATION = "operation";
    public static String SPRING_PATH_VARIABLE_PARAMETER_NAME_CRON = "cron";
    public static String SPRING_PATH_VARIABLE_PARAMETER_NAME_TASK_KEY = "taskKey";

    // .class 后缀
    public static String CLASS_SUFFIX = ".class";

    // ========= dynamic args naming ==========

    /**
     * 原 JOB 类型
     *
     * @see CommonConstants#JOB_TYPE
     */
    public static String ORIGIN_JOB_TYPE = "originJobType";


}
