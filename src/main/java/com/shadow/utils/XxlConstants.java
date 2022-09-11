package com.shadow.utils;
import jdk.internal.org.objectweb.asm.Type;


public class XxlConstants {

    private XxlConstants() {

    }

    // TIPS
    public static String PROXY_LOG_TIPS = "Xxl " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;

    public static String JOB_HANDLERRE_POSITORY = "jobHandlerRepository";

    // ♥♥♥♥♥♥♥♥ XXL 相关类及其描述符 ♥♥♥♥♥♥♥♥

    public static Type XXL_JOBEXECUTOR_TYPE = Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;");
    public static Type XXL_JOBHANDLER_TYPE = Type.getType("Lcom/xxl/job/core/handler/IJobHandler;");

}
