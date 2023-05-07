package com.shadow.core.asm.handler;

import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class XxlJobAsmHandler extends AbstractAsmHandler {

    @Override
    public void setThreadLocalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        LabelNode start = new LabelNode();
        LabelNode end = new LabelNode();
        LabelNode ret = new LabelNode();
        methodNode.tryCatchBlocks.add(new TryCatchBlockNode(start, end, ret, null));
        LabelNode line14 = new LabelNode();
        LabelNode line25 = new LabelNode();
        LabelNode line98 = new LabelNode();
        LabelNode line104 = new LabelNode();
        LabelNode line117 = new LabelNode();
        LabelNode line123 = new LabelNode();
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
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOB_EXECUTOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "com/xxl/job/core/executor/XxlJobExecutor"
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOB_EXECUTOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOB_EXECUTOR_TYPE.getDescriptor())));
        // "jobHandlerRepository"
        il.add(new LdcInsnNode(XxlConstants.JOB_HANDLER_REPOSITORY));
        // "java/lang/Class"、"getDeclaredField"、"(Ljava/lang/String;)Ljava/lang/reflect/Field;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.CLASS_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETDECLAREDFIELD, BaseConstants.REFLECT_FIELD_S, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        il.add(new InsnNode(ICONST_1));
        // "java/lang/reflect/Field"、"setAccessible"、"(Z)V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SETACCESSIBLE, BaseConstants.V_Z, false));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "java/lang/reflect/Field"、"get"、"(Ljava/lang/Object;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        // "java/util/Map"
        il.add(new TypeInsnNode(CHECKCAST, BaseConstants.MAP_TYPE.getInternalName()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "java/util/Map"、"get"、"(Ljava/lang/Object;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, BaseConstants.MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, true));
        // "com/xxl/job/core/handler/IJobHandler"
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOB_HANDLER_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        // "com/xxl/job/core/handler/IJobHandler"、"execute"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, XxlConstants.XXL_JOB_HANDLER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EXECUTE, BaseConstants.V_, false));
        il.add(new LdcInsnNode(XxlConstants.XXL_SUCCESS));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_8));
        il.add(end);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line98));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line104));
        il.add(line98);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line104);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_8));
        il.add(new InsnNode(ARETURN));
        il.add(ret);
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_9));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line117));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line123));
        il.add(line117);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line123);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_9));
        il.add(new InsnNode(ATHROW));
        methodNode.maxStack = IndexConstants.INDEX_9;
        methodNode.maxLocals = IndexConstants.INDEX_10;
    }

    @Override
    public void setNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOB_EXECUTOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "com/xxl/job/core/executor/XxlJobExecutor"
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOB_EXECUTOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOB_EXECUTOR_TYPE.getDescriptor())));
        // "jobHandlerRepository"
        il.add(new LdcInsnNode(XxlConstants.JOB_HANDLER_REPOSITORY));
        // "java/lang/Class"、"getDeclaredField"、"(Ljava/lang/String;)Ljava/lang/reflect/Field;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.CLASS_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETDECLAREDFIELD, BaseConstants.REFLECT_FIELD_S, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        il.add(new InsnNode(ICONST_1));
        // "java/lang/reflect/Field"、"setAccessible"、"(Z)V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SETACCESSIBLE, BaseConstants.V_Z, false));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "java/lang/reflect/Field"、"get"、"(Ljava/lang/Object;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        // "java/util/Map"
        il.add(new TypeInsnNode(CHECKCAST, BaseConstants.MAP_TYPE.getInternalName()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "java/util/Map"、"get"、"(Ljava/lang/Object;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, BaseConstants.MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, true));
        // "com/xxl/job/core/handler/IJobHandler"
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOB_HANDLER_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        // "com/xxl/job/core/handler/IJobHandler"、"execute"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, XxlConstants.XXL_JOB_HANDLER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EXECUTE, BaseConstants.V_, false));
        il.add(new LdcInsnNode(XxlConstants.XXL_SUCCESS));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_7;
        methodNode.maxLocals = IndexConstants.INDEX_8;
    }

    @Override
    protected void setCrudMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "com/xxl/job/core/executor/XxlJobExecutor"));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new LdcInsnNode("jobHandlerRepository"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false));
        il.add(new TypeInsnNode(CHECKCAST, "java/util/Map"));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Map", "keySet", "()Ljava/util/Set;", true));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_2;
        methodNode.maxLocals = IndexConstants.INDEX_7;
    }
}
