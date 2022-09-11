package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Type;

import static com.shadow.utils.BaseConstants.*;

public class SimpleConstants {

    private SimpleConstants() {

    }

    // TIPS
    public static String PROXY_LOG_TIPS = "Simple " + CommonConstants.TIPS;
    public static String ASM_PROXY_LOG_TIPS = "ASM " + PROXY_LOG_TIPS;
    public static String JAVASSIST_PROXY_LOG_TIPS = "JAVASSIST " + PROXY_LOG_TIPS;

    // ♦♦♦♦♦♦♦♦ SIMPLE 相关类及其描述符 ♦♦♦♦♦♦♦♦

    public static Type SIMPLE_SCHEDULETASKINFOENUM_TYPE = Type.getType("Lcom/shadow/supports/helper/ScheduleTaskInfoEnum;");
    public static Type SIMPLE_ICRONTRIGGERTASK_TYPE = Type.getType("Lcom/shadow/supports/framework/ICronTriggerTask;");
    public static Type SIMPLE_SCHEDULERESULT_TYPE = Type.getType("Lcom/shadow/supports/framework/support/ScheduleResult;");
    public static Type SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE = Type.getType("Lcom/shadow/supports/framework/CommonSchedulingConfigurer;");
    public static Type SIMPLE_SCHEDULEVO_TYPE = Type.getType("Lcom/shadow/supports/helper/ScheduleVO;");


    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lcom/shadow/supports/framework/support/ScheduleResult;
    public static String SCHEDULERESULT_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SIMPLE_SCHEDULERESULT_TYPE.getDescriptor();

    // (Ljava/lang/String;)Lcom/shadow/supports/helper/ScheduleVO;
    public static String SCHEDULEVO_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + SIMPLE_SCHEDULEVO_TYPE.getDescriptor();

    // (Lcom/shadow/supports/framework/ICronTriggerTask;)Ljava/lang/Boolean;
    public static String BOOLEAN_ICRONTRIGGERTASK = CommonConstants.LEFT_BRACKETS + SIMPLE_ICRONTRIGGERTASK_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + BaseConstants.BOOLEAN_TYPE.getDescriptor();

    // (Ljava/lang/String;)Lorg/springframework/scheduling/support/CronTrigger;
    public static String CRONTRIGGER_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + SpringConstants.SPRING_CRONTRIGGER_TYPE.getDescriptor();

}
