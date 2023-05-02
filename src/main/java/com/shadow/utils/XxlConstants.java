package com.shadow.utils;
import jdk.internal.org.objectweb.asm.Type;


public class XxlConstants {

    private XxlConstants() {

    }
    // tips
    public static String XXL_SUCCESS = "Execute Xxl Job Successful !";

    public static String JOB_HANDLER_REPOSITORY = "jobHandlerRepository";

    // ♥♥♥♥♥♥♥♥ XXL 相关类及其描述符 ♥♥♥♥♥♥♥♥

    public static Type XXL_JOB_EXECUTOR_TYPE = Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;");
    public static Type XXL_JOB_HANDLER_TYPE = Type.getType("Lcom/xxl/job/core/handler/IJobHandler;");

}
