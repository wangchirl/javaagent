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
    public static String SIMPLE_COMMONSCHEDULINGCONFIGURER_CLASS = "com.shadow.supports.framework.CommonSchedulingConfigurer";
    public static String SIMPLE_SCHEDULEVO_CLASS = "com.shadow.supports.helper.ScheduleVO";

    /**
     * internal class name
     */
    public static String SIMPLE_SCHEDULETASKINFOENUM_INTERNAL_CLASS = SIMPLE_SCHEDULETASKINFOENUM_CLASS.replaceAll(DOT, BIAS);
    public static String SIMPLE_ICRONTRIGGERTASK_INTERNAL_CLASS = SIMPLE_ICRONTRIGGERTASK_CLASS.replaceAll(DOT, BIAS);
    public static String SIMPLE_SCHEDULERESULT_INTERNAL_CLASS = SIMPLE_SCHEDULERESULT_CLASS.replaceAll(DOT, BIAS);
    public static String SIMPLE_COMMONSCHEDULINGCONFIGURER_INTERNAL_CLASS = SIMPLE_COMMONSCHEDULINGCONFIGURER_CLASS.replaceAll(DOT, BIAS);
    public static String SIMPLE_SCHEDULEVO_INTERNAL_CLASS = SIMPLE_SCHEDULEVO_CLASS.replaceAll(DOT, BIAS);

    // Lcom/shadow/supports/helper/ScheduleTaskInfoEnum;
    public static String SIMPLE_SCHEDULETASKINFOENUM_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_SCHEDULETASKINFOENUM_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lcom/shadow/supports/framework/ICronTriggerTask;
    public static String SIMPLE_ICRONTRIGGERTASK_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_ICRONTRIGGERTASK_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lcom/shadow/supports/framework/support/ScheduleResult;
    public static String SIMPLE_SCHEDULERESULT_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_SCHEDULERESULT_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lcom/shadow/supports/framework/CommonSchedulingConfigurer;
    public static String SIMPLE_COMMONSCHEDULINGCONFIGURER_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_COMMONSCHEDULINGCONFIGURER_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Lcom/shadow/supports/helper/ScheduleVO;
    public static String SIMPLE_SCHEDULEVO_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SIMPLE_SCHEDULEVO_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lcom/shadow/supports/framework/support/ScheduleResult;
    public static String SCHEDULERESULT_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SIMPLE_SCHEDULERESULT_DESCRIPTOR;

    // (Ljava/lang/String;)Lcom/shadow/supports/helper/ScheduleVO;
    public static String SCHEDULEVO_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + SIMPLE_SCHEDULEVO_DESCRIPTOR;

    // (Lcom/shadow/supports/framework/ICronTriggerTask;)Ljava/lang/Boolean;
    public static String BOOLEAN_ICRONTRIGGERTASK = CommonConstants.LEFT_BRACKETS + SIMPLE_ICRONTRIGGERTASK_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + BaseConstants.BOOLEAN_DESCRIPTOR;

    // (Ljava/lang/String;)Lorg/springframework/scheduling/support/CronTrigger;
    public static String CRONTRIGGER_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + SpringConstants.SPRING_CRONTRIGGER_DESCRIPTOR;

}
