package com.shadow.core.asm.handler;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;


public class SimpleJobAsmHandler extends AbstractAsmHandler {

    @Override
    public void setThreadLocalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        // try -finally
        LabelNode start = new LabelNode();
        LabelNode end = new LabelNode();
        LabelNode ret = new LabelNode();
        methodNode.tryCatchBlocks.add(new TryCatchBlockNode(start, end, ret, null));

        LabelNode line14 = new LabelNode();
        LabelNode line25 = new LabelNode();
        LabelNode line73 = new LabelNode();
        LabelNode line79 = new LabelNode();
        LabelNode line92 = new LabelNode();
        LabelNode line98 = new LabelNode();
        il.add(start);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNULL, line14));
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        // "java/lang/ThreadLocal"、"set"、"(Ljava/lang/Object;)V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false));
        il.add(new JumpInsnNode(GOTO, line25));
        il.add(line14);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line25));
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        // "java/lang/ThreadLocal"、"set"、"(Ljava/lang/Object;)V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false));
        il.add(line25);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "com/shadow/supports/helper/ScheduleTaskInfoEnum"、"getScheduleTaskBeanNameByTaskKey"、"(Ljava/lang/String;)Ljava/lang/String;"
        il.add(new MethodInsnNode(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULE_TASK_INFO_ENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKBEANNAMEBYTASKKEY, BaseConstants.S_S, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "Lcom/shadow/supports/framework/ICronTriggerTask;"
        il.add(new LdcInsnNode(Type.getType(SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S_C_, true));
        // "com/shadow/supports/framework/ICronTriggerTask"
        il.add(new TypeInsnNode(CHECKCAST, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        // "com/shadow/supports/framework/ICronTriggerTask"、"run"、"()V"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, true));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        // "com/shadow/supports/framework/ICronTriggerTask"、"getResult"、"()Lcom/shadow/supports/framework/support/ScheduleResult;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRESULT, SimpleConstants.SCHEDULE_RESULT_, true));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(end);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line73));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line79));
        il.add(line73);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line79);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new InsnNode(ARETURN));
        il.add(ret);
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line92));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line98));
        il.add(line92);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line98);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        il.add(new InsnNode(ATHROW));
        methodNode.maxStack = IndexConstants.INDEX_7;
        methodNode.maxLocals = IndexConstants.INDEX_8;
    }

    @Override
    public void setNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "com/shadow/supports/helper/ScheduleTaskInfoEnum"、"getScheduleTaskBeanNameByTaskKey"、"(Ljava/lang/String;)Ljava/lang/String;"
        il.add(new MethodInsnNode(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULE_TASK_INFO_ENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKBEANNAMEBYTASKKEY, BaseConstants.S_S, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "Lcom/shadow/supports/framework/ICronTriggerTask;"
        il.add(new LdcInsnNode(Type.getType(SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S_C_, true));
        // "com/shadow/supports/framework/ICronTriggerTask"
        il.add(new TypeInsnNode(CHECKCAST, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        // "com/shadow/supports/framework/ICronTriggerTask"、"run"、"()V"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, true));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        // "com/shadow/supports/framework/ICronTriggerTask"、"getResult"、"()Lcom/shadow/supports/framework/support/ScheduleResult;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRON_TRIGGER_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRESULT, SimpleConstants.SCHEDULE_RESULT_, true));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_5;
        methodNode.maxLocals = IndexConstants.INDEX_6;
    }

    @Override
    public void addFields(int api, ClassNode cn) throws Exception {
        // 创建 CRUD 需要的字段
        FieldNode fieldNode = setCrudClassField(api);
        // 加入 Class
        cn.fields.add(fieldNode);
    }

    private FieldNode setCrudClassField(int api) {
        FieldNode fieldNode = new FieldNode(
                api,
                Opcodes.ACC_PRIVATE,
                getArgs().getCrudFieldName(),
                SimpleConstants.SIMPLE_COMMON_SCHEDULING_CONFIGURER_TYPE.getDescriptor(),
                null,
                null);
        fieldNode.visitAnnotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getDescriptor(), true);
        return fieldNode;
    }

    @Override
    public void setCrudMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ILOAD, IndexConstants.INDEX_1));
        LabelNode labelNode0 = new LabelNode();
        LabelNode labelNode1 = new LabelNode();
        LabelNode labelNode2 = new LabelNode();
        LabelNode labelNode3 = new LabelNode();
        il.add(new TableSwitchInsnNode(IndexConstants.INDEX_0, IndexConstants.INDEX_2, labelNode3, new LabelNode[] { labelNode0, labelNode1, labelNode2 }));
        il.add(labelNode0);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;"));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "cancel", "(Ljava/lang/String;)Ljava/lang/Boolean;", false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        LabelNode labelNode4 = new LabelNode();
        il.add(new JumpInsnNode(GOTO, labelNode4));
        il.add(labelNode1);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;"));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "update", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;", false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new JumpInsnNode(GOTO, labelNode4));
        il.add(labelNode2);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new MethodInsnNode(INVOKESTATIC, "com/shadow/supports/helper/ScheduleTaskInfoEnum", "getScheduleTaskBeanNameByTaskKey", "(Ljava/lang/String;)Ljava/lang/String;", false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        il.add(new LdcInsnNode(Type.getType("Lcom/shadow/supports/framework/ICronTriggerTask;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new TypeInsnNode(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask"));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getEnvironment", "()Lorg/springframework/core/env/Environment;", true));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new MethodInsnNode(INVOKESTATIC, "com/shadow/supports/helper/ScheduleTaskInfoEnum", "getScheduleTaskCronNameByTaskKey", "(Ljava/lang/String;)Ljava/lang/String;", false));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/core/env/Environment", "getProperty", "(Ljava/lang/String;)Ljava/lang/String;", true));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "setTrigger", "(Ljava/lang/String;)Lorg/springframework/scheduling/support/CronTrigger;", true));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        LabelNode labelNode5 = new LabelNode();
        il.add(new JumpInsnNode(IFNULL, labelNode5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/String", "length", "()I", false));
        il.add(new IntInsnNode(BIPUSH, IndexConstants.INDEX_6));
        il.add(new JumpInsnNode(IF_ICMPLE, labelNode5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new TypeInsnNode(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask"));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "setTrigger", "(Ljava/lang/String;)Lorg/springframework/scheduling/support/CronTrigger;", true));
        il.add(new InsnNode(POP));
        il.add(labelNode5);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;"));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new TypeInsnNode(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "add", "(Lcom/shadow/supports/framework/ICronTriggerTask;)Ljava/lang/Boolean;", false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new JumpInsnNode(GOTO, labelNode4));
        il.add(labelNode3);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getCrudFieldName(), "Lcom/shadow/supports/framework/CommonSchedulingConfigurer;"));
        il.add(new InsnNode(ACONST_NULL));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "com/shadow/supports/framework/CommonSchedulingConfigurer", "get", "(Ljava/lang/String;)Lcom/shadow/supports/helper/ScheduleVO;", false));
        il.add(new InsnNode(ARETURN));
        il.add(labelNode4);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_3;
        methodNode.maxLocals = IndexConstants.INDEX_7;
    }
}
