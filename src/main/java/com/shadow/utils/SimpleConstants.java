package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Type;

import static com.shadow.utils.BaseConstants.*;

public class SimpleConstants {

    private SimpleConstants() {

    }

    // ♦♦♦♦♦♦♦♦ SIMPLE 相关类及其描述符 ♦♦♦♦♦♦♦♦

    public static Type SIMPLE_SCHEDULE_TASK_INFO_ENUM_TYPE = Type.getType("Lcom/shadow/supports/helper/ScheduleTaskInfoEnum;");
    public static Type SIMPLE_ICRON_TRIGGER_TASK_TYPE = Type.getType("Lcom/shadow/supports/framework/ICronTriggerTask;");
    public static Type SIMPLE_SCHEDULE_RESULT_TYPE = Type.getType("Lcom/shadow/supports/framework/support/ScheduleResult;");
    public static Type SIMPLE_COMMON_SCHEDULING_CONFIGURER_TYPE = Type.getType("Lcom/shadow/supports/framework/CommonSchedulingConfigurer;");
    public static Type SIMPLE_SCHEDULEVO_TYPE = Type.getType("Lcom/shadow/supports/helper/ScheduleVO;");


    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()Lcom/shadow/supports/framework/support/ScheduleResult;
    public static String SCHEDULE_RESULT_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SIMPLE_SCHEDULE_RESULT_TYPE.getDescriptor();

    // (Ljava/lang/String;)Lcom/shadow/supports/helper/ScheduleVO;
    public static String SCHEDULEVO_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + SIMPLE_SCHEDULEVO_TYPE.getDescriptor();

    // (Lcom/shadow/supports/framework/ICronTriggerTask;)Ljava/lang/Boolean;
    public static String BOOLEAN_ICRON_TRIGGER_TASK = CommonConstants.LEFT_BRACKETS + SIMPLE_ICRON_TRIGGER_TASK_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + BaseConstants.BOOLEAN_TYPE.getDescriptor();

    // (Ljava/lang/String;)Lorg/springframework/scheduling/support/CronTrigger;
    public static String CRONTRIGGER_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + SpringConstants.SPRING_CRON_TRIGGER_TYPE.getDescriptor();

}
