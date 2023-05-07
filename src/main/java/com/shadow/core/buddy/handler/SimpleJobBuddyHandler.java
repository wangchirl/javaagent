package com.shadow.core.buddy.handler;

import static net.bytebuddy.jar.asm.Opcodes.*;

import com.shadow.utils.*;
import net.bytebuddy.jar.asm.*;

public class SimpleJobBuddyHandler extends AbstractBuddyHandler {

    @Override
    public void setThreadLocalMethodBody(MethodVisitor methodVisitor) {
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, null);
        Label label3 = new Label();
        methodVisitor.visitTryCatchBlock(label2, label3, label2, null);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        Label label4 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label4);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false);
        Label label5 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitJumpInsn(IFNULL, label5);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false);
        methodVisitor.visitLabel(label5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULE_TASK_INFO_ENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKBEANNAMEBYTASKKEY, BaseConstants.S_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(Type.getType(SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, true);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRESULT, SimpleConstants.SCHEDULE_RESULT_, true);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        Label label6 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        Label label7 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label7);
        methodVisitor.visitLabel(label6);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor());
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false);
        methodVisitor.visitLabel(label7);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_7);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        Label label8 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label8);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        Label label9 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label9);
        methodVisitor.visitLabel(label8);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor());
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false);
        methodVisitor.visitLabel(label9);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_7);
        methodVisitor.visitInsn(ATHROW);
    }

    @Override
    public void setNormalMethodBody(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULE_TASK_INFO_ENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKBEANNAMEBYTASKKEY, BaseConstants.S_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(Type.getType(SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, true);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRESULT, SimpleConstants.SCHEDULE_RESULT_, true);
        methodVisitor.visitInsn(ARETURN);
    }

    @Override
    public void addFields(ClassVisitor classVisitor) {
        FieldVisitor visitField = classVisitor.visitField(
                ACC_PRIVATE,
                getArgs().getCrudFieldName(),
                SimpleConstants.SIMPLE_COMMON_SCHEDULING_CONFIGURER_TYPE.getDescriptor(),
                null,
                null
        );
        visitField.visitAnnotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getDescriptor(), true);
        visitField.visitEnd();
    }

    @Override
    public void setCrudMethodBody(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(ILOAD, 1);
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitTableSwitchInsn(0, 2, label3, new Label[] { label0, label1, label2 });
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;");
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "cancel", "(Ljava/lang/String;)Ljava/lang/Boolean;", false);
        methodVisitor.visitVarInsn(ASTORE, 4);
        Label label4 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label4);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;");
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "update", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;", false);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitJumpInsn(GOTO, label4);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "com/shadow/supports/helper/ScheduleTaskInfoEnum", "getScheduleTaskBeanNameByTaskKey", "(Ljava/lang/String;)Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 5);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), "Lorg/springframework/context/ApplicationContext;");
        methodVisitor.visitVarInsn(ALOAD, 5);
        methodVisitor.visitLdcInsn(Type.getType("Lcom/shadow/supports/framework/ICronTriggerTask;"));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", true);
        methodVisitor.visitVarInsn(ASTORE, 6);
        methodVisitor.visitVarInsn(ALOAD, 6);
        methodVisitor.visitTypeInsn(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), "Lorg/springframework/context/ApplicationContext;");
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getEnvironment", "()Lorg/springframework/core/env/Environment;", true);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "com/shadow/supports/helper/ScheduleTaskInfoEnum", "getScheduleTaskCronNameByTaskKey", "(Ljava/lang/String;)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "org/springframework/core/env/Environment", "getProperty", "(Ljava/lang/String;)Ljava/lang/String;", true);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "setTrigger", "(Ljava/lang/String;)Lorg/springframework/scheduling/support/CronTrigger;", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 3);
        Label label5 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label5);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "length", "()I", false);
        methodVisitor.visitIntInsn(BIPUSH, 6);
        methodVisitor.visitJumpInsn(IF_ICMPLE, label5);
        methodVisitor.visitVarInsn(ALOAD, 6);
        methodVisitor.visitTypeInsn(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask");
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "setTrigger", "(Ljava/lang/String;)Lorg/springframework/scheduling/support/CronTrigger;", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label5);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;");
        methodVisitor.visitVarInsn(ALOAD, 6);
        methodVisitor.visitTypeInsn(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "add", "(Lcom/shadow/supports/framework/ICronTriggerTask;)Ljava/lang/Boolean;", false);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitJumpInsn(GOTO, label4);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;");
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "get", "(Ljava/lang/String;)Lcom/shadow/supports/helper/ScheduleVO;", false);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitInsn(ARETURN);
    }
}
