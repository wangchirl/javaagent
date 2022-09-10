package com.shadow.utils;
import static com.shadow.utils.CommonConstants.*;
import static com.shadow.utils.BaseConstants.*;

public class SimpleConstants {

    private SimpleConstants() {

    }

    // TIPS
    public static String PROXY_LOG_TIPS = "Simple " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;

    // ♦♦♦♦♦♦♦♦ SIMPLE 相关类及其描述符 ♦♦♦♦♦♦♦♦

    /**
     * class name
     */
    public static String SIMPLE_SCHEDULETASKINFOENUM_CLASS = "com.shadow.supports.helper.ScheduleTaskInfoEnum";
    public static String SIMPLE_ICRONTRIGGERTASK_CLASS = "com.shadow.supports.framework.ICronTriggerTask";
    public static String SIMPLE_SCHEDULERESULT_CLASS = "com.shadow.supports.framework.support.ScheduleResult";

    /**
     * internal class name
     */
    public static String SIMPLE_SCHEDULETASKINFOENUM_INTERNAL_CLASS = SIMPLE_SCHEDULETASKINFOENUM_CLASS.replaceAll(DOT, BIAS);
    public static String SIMPLE_ICRONTRIGGERTASK_INTERNAL_CLASS = SIMPLE_ICRONTRIGGERTASK_CLASS.replaceAll(DOT, BIAS);
    public static String SIMPLE_SCHEDULERESULT_INTERNAL_CLASS = SIMPLE_SCHEDULERESULT_CLASS.replaceAll(DOT, BIAS);

    // Lcom/shadow/supports/helper/ScheduleTaskInfoEnum;
    public static String SIMPLE_SCHEDULETASKINFOENUM_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_SCHEDULETASKINFOENUM_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lcom/shadow/supports/framework/ICronTriggerTask;
    public static String SIMPLE_ICRONTRIGGERTASK_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_ICRONTRIGGERTASK_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lcom/shadow/supports/framework/support/ScheduleResult;
    public static String SIMPLE_SCHEDULERESULT_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_SCHEDULERESULT_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lcom/shadow/supports/framework/support/ScheduleResult;
    public static String SCHEDULERESULT_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SIMPLE_SCHEDULERESULT_DESCRIPTOR;

}
