package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Opcodes;

public class CommonConstants {

    private CommonConstants() {

    }

    public static final String JAVASSIST_HANDLER_NAME_SUFFIX = "JobJavassistHandler";
    public static final String ASM_HANDLER_NAME_SUFFIX = "JobAsmHandler";
    public static final String BYTEBUDDY_HANDLER_NAME_SUFFIX = "JobBuddyHandler";

    /**
     * 常见符号
     */
    public static String EQUAL = "=";
    public static String COMM = ",";
    public static String REG_DOT = "\\.";
    public static String DOT = ".";
    public static String BIAS = "/";
    public static String AND = "&";
    public static String AT = "@";
    public static String OR = "|";
    public static String SPACE = " ";
    public static String SEMICOLON = ";";
    public static String LEFT_BRACKETS = "(";
    public static String RIGHT_BRACKETS = ")";
    public static String LEFT_BIG_BRACKETS = "[";
    public static String RIGHT_BIG_BRACKETS = "]";
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
        return ProxyTypeEnum.ASM;
    }

    /**
     * 定时任务类型枚举
     */
    public enum JobTypeEnum {
        XXL,
        QUARTZ,
        SPRING,
        SIMPLE
    }

    public static JobTypeEnum getByJobTypeName(String type) {
        for (JobTypeEnum jobTypeEnum : JobTypeEnum.values()) {
            if (jobTypeEnum.name().equalsIgnoreCase(type)) {
                return jobTypeEnum;
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
     * 默认注入 controller 的 ioc 容器字段名称
     */
    public static String DEFAULT_IOC_FIELD_VALUE = "$$$springIoc$$$";

    /**
     * Simple Job IOC 对象类型
     */
    public static String SIMPLE_JOB_IOC_FIELD = "private com.shadow.supports.framework.CommonSchedulingConfigurer ";

    /**
     * 默认 Simple Job 定时任务容器对象字段名称
     */
    public static String DEFAULT_SIMPLE_JOB_IOC_FIELD_NAME = "$$$simpleJobConfigurer$$$";

    /**
     * crud 方法名称
     */
    public static String DEFAULT_CRUD_METHOD_NAME = "$$$jobCrud$$$";

    // ASM version
    public static int ASM_API_VERSION = Opcodes.ASM5;

    // 访问修饰符
    public static String ACC_PUBLIC = "public";
    public static String ACC_PROTECTED = "protected";
    public static String ACC_PRIVATE = "private";

    // 常量字段值
    public static String CONST_NAME = "name";
    public static String CONST_PATH = "path";
    public static String CONST_VALUE = "value";
    public static String CONST_REQUIRED = "required";
    public static String CONST_PARAMS = "params";
    public static String CONST_BODY = "body";
    public static String CONST_OPERATION = "operation";
    public static String CONST_CRON = "cron";
    public static String CONST_TASK_KEY = "taskKey";

    // .class 后缀
    public static String CLASS_SUFFIX = ".class";

}
