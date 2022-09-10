package com.shadow.utils;
import static com.shadow.utils.CommonConstants.*;
import static com.shadow.utils.BaseConstants.*;

public class XxlConstants {

    private XxlConstants() {

    }

    // TIPS
    public static String PROXY_LOG_TIPS = "Xxl " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;

    public static String JOB_HANDLERRE_POSITORY = "jobHandlerRepository";

    // ♥♥♥♥♥♥♥♥ XXL 相关类及其描述符 ♥♥♥♥♥♥♥♥

    /**
     * class name
     */
    public static String XXL_JOBEXECUTOR_CLASS = "com.xxl.job.core.executor.XxlJobExecutor";
    public static String XXL_JOBHANDLER_CLASS = "com.xxl.job.core.handler.IJobHandler";

    /**
     * internal class name
     */
    public static String XXL_JOBEXECUTOR_INTERNAL_CLASS = XXL_JOBEXECUTOR_CLASS.replaceAll(DOT, BIAS);
    public static String XXL_JOBHANDLER_INTERNAL_CLASS = XXL_JOBHANDLER_CLASS.replaceAll(DOT, BIAS);

    // Lcom/xxl/job/core/executor/XxlJobExecutor;
    public static String XXL_JOBEXECUTOR_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + XXL_JOBEXECUTOR_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lcom/xxl/job/core/handler/IJobHandler;
    public static String XXL_JOBHANDLER_INTERNAL_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + XXL_JOBHANDLER_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

}
