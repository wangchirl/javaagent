package com.shadow.core.asm.handler;

import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;


import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static net.bytebuddy.jar.asm.Opcodes.ASTORE;

public class SpringJobAsmHandler extends AbstractAsmHandler {

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
        LabelNode line57 = new LabelNode();
        LabelNode line119 = new LabelNode();
        LabelNode line122 = new LabelNode();
        LabelNode line134 = new LabelNode();
        LabelNode line153 = new LabelNode();
        LabelNode line159 = new LabelNode();
        LabelNode line140 = new LabelNode();
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
        // "Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;"
        il.add(new LdcInsnNode(Type.getType(SpringConstants.SPRING_SCHEDULED_ANNOTATION_BEAN_POSTPROCESSOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_SCHEDULED_ANNOTATION_BEAN_POSTPROCESSOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"、"getScheduledTasks"、"()Ljava/util/Set;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_ANNOTATION_BEAN_POSTPROCESSOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULEDTASKS, BaseConstants.SET_, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_5));
        // "java/util/Set"、"iterator"、"()Ljava/util/Iterator;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, BaseConstants.SET_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_ITERATOR, BaseConstants.ITERATOR_, true));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(line57);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        // "java/util/Iterator"、"hasNext"、"()Z"
        il.add(new MethodInsnNode(INVOKEINTERFACE, BaseConstants.ITERATOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_HASNEXT, BaseConstants.Z_, true));
        il.add(new JumpInsnNode(IFEQ, line122));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        // "java/util/Iterator"、"next"、"()Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, BaseConstants.ITERATOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_NEXT, BaseConstants.O_, true));
        // "org/springframework/scheduling/config/ScheduledTask"
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_SCHEDULED_TASK_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        // "org/springframework/scheduling/config/ScheduledTask"、"getTask"、"()Lorg/springframework/scheduling/config/Task;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETTASK, SpringConstants.TASK_, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_8));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_8));
        // "org/springframework/scheduling/config/Task"、"getRunnable"、"()Ljava/lang/Runnable;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRUNNABLE, BaseConstants.RUNNABLE_, false));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_SCHEDULED_METHOD_RUNNABLE_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_9));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"getMethod"、"()Ljava/lang/reflect/Method;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_METHOD_RUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETMETHOD, BaseConstants.REFLECT_METHOD_, false));
        // "java/lang/reflect/Method"、"getName"、"()Ljava/lang/String;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.REFLECT_METHOD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETNAME, BaseConstants.S_, false));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "java/lang/String"、"equals"、"(Ljava/lang/Object;)Z"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.STRING_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EQUALS, BaseConstants.Z_O, false));
        il.add(new JumpInsnNode(IFEQ, line119));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"run"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_METHOD_RUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, false));
        il.add(new JumpInsnNode(GOTO, line122));
        il.add(line119);
        il.add(new JumpInsnNode(GOTO, line57));
        il.add(line122);
        il.add(new LdcInsnNode(SpringConstants.SPRING_SUCCESS));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(end);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line134));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line140));
        il.add(line134);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line140);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_6));
        il.add(new InsnNode(ARETURN));
        il.add(ret);
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_10));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line153));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line159));
        il.add(line153);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREAD_LOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREAD_LOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line159);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_10));
        il.add(new InsnNode(ATHROW));
        methodNode.maxStack = IndexConstants.INDEX_10;
        methodNode.maxLocals = IndexConstants.INDEX_11;

    }

    @Override
    public void setNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        LabelNode line32 = new LabelNode();
        LabelNode line94 = new LabelNode();
        LabelNode line97 = new LabelNode();
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(Opcodes.GETFIELD, getInnerClassName(), getArgs().getIocFieldName(), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;"
        il.add(new LdcInsnNode(Type.getType(SpringConstants.SPRING_SCHEDULED_ANNOTATION_BEAN_POSTPROCESSOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, SpringConstants.SPRING_SCHEDULED_ANNOTATION_BEAN_POSTPROCESSOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_4));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_4));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"、"getScheduledTasks"、"()Ljava/util/Set;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_ANNOTATION_BEAN_POSTPROCESSOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULEDTASKS, BaseConstants.SET_, false));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_5));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_5));
        // "java/util/Set"、"iterator"、"()Ljava/util/Iterator;"
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, BaseConstants.SET_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_ITERATOR, BaseConstants.ITERATOR_, true));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_6));
        il.add(line32);
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_6));
        // "java/util/Iterator"、"hasNext"、"()Z"
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, BaseConstants.ITERATOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_HASNEXT, BaseConstants.Z_, true));
        il.add(new JumpInsnNode(Opcodes.IFEQ, line97));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_6));
        // "java/util/Iterator"、"next"、"()Ljava/lang/Object;"
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, BaseConstants.ITERATOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_NEXT, BaseConstants.O_, true));
        // "org/springframework/scheduling/config/ScheduledTask"
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, SpringConstants.SPRING_SCHEDULED_TASK_TYPE.getInternalName()));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_7));
        // "org/springframework/scheduling/config/ScheduledTask"、"getTask"、"()Lorg/springframework/scheduling/config/Task;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETTASK, SpringConstants.TASK_, false));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_8));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_8));
        // "org/springframework/scheduling/config/Task"、"getRunnable"、"()Ljava/lang/Runnable;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRUNNABLE, BaseConstants.RUNNABLE_, false));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, SpringConstants.SPRING_SCHEDULED_METHOD_RUNNABLE_TYPE.getInternalName()));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_9));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"getMethod"、"()Ljava/lang/reflect/Method;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_METHOD_RUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETMETHOD, BaseConstants.REFLECT_METHOD_, false));
        // "java/lang/reflect/Method"、"getName"、"()Ljava/lang/String;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, BaseConstants.REFLECT_METHOD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETNAME, BaseConstants.S_, false));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_1));
        // "java/lang/String"、"equals"、"(Ljava/lang/Object;)Z"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, BaseConstants.STRING_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EQUALS, BaseConstants.Z_O, false));
        il.add(new JumpInsnNode(Opcodes.IFEQ, line94));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"run"、"()V"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULED_METHOD_RUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, false));
        il.add(new JumpInsnNode(Opcodes.GOTO, line97));
        il.add(line94);
        il.add(new JumpInsnNode(Opcodes.GOTO, line32));
        il.add(line97);
        il.add(new LdcInsnNode(SpringConstants.SPRING_SUCCESS));
        il.add(new InsnNode(Opcodes.ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_9;
        methodNode.maxLocals = IndexConstants.INDEX_10;
    }

    @Override
    protected void setCrudMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        LabelNode labelNode108 = new LabelNode();
        LabelNode labelNode147 = new LabelNode();
        LabelNode labelNode340 = new LabelNode();
        LabelNode labelNode351 = new LabelNode();
        LabelNode labelNode402 = new LabelNode();
        LabelNode labelNode466 = new LabelNode();
        LabelNode labelNode457 = new LabelNode();
        LabelNode labelNode460 = new LabelNode();
        LabelNode labelNode463 = new LabelNode();
        LabelNode labelNode505 = new LabelNode();
        LabelNode labelNode522 = new LabelNode();
        il.add(new InsnNode(ICONST_0));
        il.add(new VarInsnNode(ISTORE,4));
        il.add(new VarInsnNode(ALOAD,0));
        il.add(new FieldInsnNode(GETFIELD,getInnerClassName(),getArgs().getIocFieldName(),"Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"org/springframework/context/ApplicationContext","getBean","(Ljava/lang/Class;)Ljava/lang/Object;",true));
        il.add(new TypeInsnNode(CHECKCAST,"org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"));
        il.add(new VarInsnNode(ASTORE,5));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;")));
        il.add(new LdcInsnNode("scheduledTasks"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/Class","getDeclaredField","(Ljava/lang/String;)Ljava/lang/reflect/Field;",false));
        il.add(new VarInsnNode(ASTORE,6));
        il.add(new VarInsnNode(ALOAD,6));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Field","setAccessible","(Z)V",false));
        il.add(new VarInsnNode(ALOAD,6));
        il.add(new VarInsnNode(ALOAD,5));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Field","get","(Ljava/lang/Object;)Ljava/lang/Object;",false));
        il.add(new TypeInsnNode(CHECKCAST,"java/util/Map"));
        il.add(new VarInsnNode(ASTORE,7));
        il.add(new InsnNode(ACONST_NULL));
        il.add(new VarInsnNode(ASTORE,8));
        il.add(new TypeInsnNode(NEW,"java/util/HashSet"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL,"java/util/HashSet","<init>","()V",false));
        il.add(new VarInsnNode(ASTORE,9));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;")));
        il.add(new LdcInsnNode("registrar"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/Class","getDeclaredField","(Ljava/lang/String;)Ljava/lang/reflect/Field;",false));
        il.add(new VarInsnNode(ASTORE,10));
        il.add(new VarInsnNode(ALOAD,10));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Field","setAccessible","(Z)V",false));
        il.add(new VarInsnNode(ALOAD,10));
        il.add(new VarInsnNode(ALOAD,5));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Field","get","(Ljava/lang/Object;)Ljava/lang/Object;",false));
        il.add(new TypeInsnNode(CHECKCAST,"org/springframework/scheduling/config/ScheduledTaskRegistrar"));
        il.add(new VarInsnNode(ASTORE,11));
        il.add(new TypeInsnNode(NEW,"java/util/HashMap"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL,"java/util/HashMap","<init>","()V",false));
        il.add(new VarInsnNode(ASTORE,12));
        il.add(new VarInsnNode(ALOAD,7));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map","entrySet","()Ljava/util/Set;",true));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Set","iterator","()Ljava/util/Iterator;",true));
        il.add(new VarInsnNode(ASTORE,13));
        il.add(labelNode108);
        il.add(new VarInsnNode(ALOAD,13));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Iterator","hasNext","()Z",true));
        il.add(new JumpInsnNode(IFEQ,labelNode466));
        il.add(new VarInsnNode(ALOAD,13));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Iterator","next","()Ljava/lang/Object;",true));
        il.add(new TypeInsnNode(CHECKCAST,"java/util/Map$Entry"));
        il.add(new VarInsnNode(ASTORE,14));
        il.add(new VarInsnNode(ALOAD,14));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map$Entry","getValue","()Ljava/lang/Object;",true));
        il.add(new TypeInsnNode(CHECKCAST,"java/util/Set"));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Set","iterator","()Ljava/util/Iterator;",true));
        il.add(new VarInsnNode(ASTORE,15));
        il.add(labelNode147);
        il.add(new VarInsnNode(ALOAD,15));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Iterator","hasNext","()Z",true));
        il.add(new JumpInsnNode(IFEQ,labelNode463));
        il.add(new VarInsnNode(ALOAD,15));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Iterator","next","()Ljava/lang/Object;",true));
        il.add(new TypeInsnNode(CHECKCAST,"org/springframework/scheduling/config/ScheduledTask"));
        il.add(new VarInsnNode(ASTORE,16));
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/ScheduledTask","getTask","()Lorg/springframework/scheduling/config/Task;",false));
        il.add(new TypeInsnNode(CHECKCAST,"org/springframework/scheduling/config/CronTask"));
        il.add(new VarInsnNode(ASTORE,17));
        il.add(new VarInsnNode(ALOAD,17));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/CronTask","getRunnable","()Ljava/lang/Runnable;",false));
        il.add(new TypeInsnNode(CHECKCAST,"org/springframework/scheduling/support/ScheduledMethodRunnable"));
        il.add(new VarInsnNode(ASTORE,18));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/config/ScheduledTask;")));
        il.add(new LdcInsnNode("future"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/Class","getDeclaredField","(Ljava/lang/String;)Ljava/lang/reflect/Field;",false));
        il.add(new VarInsnNode(ASTORE,19));
        il.add(new VarInsnNode(ALOAD,19));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Field","setAccessible","(Z)V",false));
        il.add(new VarInsnNode(ALOAD,19));
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Field","get","(Ljava/lang/Object;)Ljava/lang/Object;",false));
        il.add(new TypeInsnNode(CHECKCAST,"java/util/concurrent/ScheduledFuture"));
        il.add(new VarInsnNode(ASTORE,20));
        il.add(new VarInsnNode(ALOAD,20));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/concurrent/ScheduledFuture","isCancelled","()Z",true));
        il.add(new VarInsnNode(ISTORE,21));
        il.add(new VarInsnNode(ALOAD,12));
        il.add(new VarInsnNode(ALOAD,18));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/support/ScheduledMethodRunnable","getMethod","()Ljava/lang/reflect/Method;",false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Method","getName","()Ljava/lang/String;",false));
        il.add(new TypeInsnNode(NEW,"java/lang/StringBuilder"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL,"java/lang/StringBuilder","<init>","()V",false));
        il.add(new VarInsnNode(ALOAD,17));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/CronTask","getExpression","()Ljava/lang/String;",false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/StringBuilder","append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",false));
        il.add(new LdcInsnNode(" 是否停止: "));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/StringBuilder","append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",false));
        il.add(new VarInsnNode(ILOAD,21));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/StringBuilder","append","(Z)Ljava/lang/StringBuilder;",false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/StringBuilder","toString","()Ljava/lang/String;",false));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map","put","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",true));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ALOAD,18));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/support/ScheduledMethodRunnable","getMethod","()Ljava/lang/reflect/Method;",false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Method","getName","()Ljava/lang/String;",false));
        il.add(new VarInsnNode(ALOAD,2));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/String","equals","(Ljava/lang/Object;)Z",false));
        il.add(new JumpInsnNode(IFEQ,labelNode460));
        il.add(new VarInsnNode(ALOAD,14));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map$Entry","getKey","()Ljava/lang/Object;",true));
        il.add(new VarInsnNode(ASTORE,8));
        il.add(new VarInsnNode(ALOAD,9));
        il.add(new VarInsnNode(ALOAD,14));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map$Entry","getValue","()Ljava/lang/Object;",true));
        il.add(new TypeInsnNode(CHECKCAST,"java/util/Collection"));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Set","addAll","(Ljava/util/Collection;)Z",true));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ILOAD,1));
        il.add(new TableSwitchInsnNode(0,2, labelNode457,new LabelNode[]{labelNode340,labelNode351,labelNode402}));
        il.add(labelNode340);
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/ScheduledTask","cancel","()V",false));
        il.add(new InsnNode(ICONST_1));
        il.add(new VarInsnNode(ISTORE,4));
        il.add(new JumpInsnNode(GOTO,labelNode460));
        il.add(labelNode351);
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/ScheduledTask","cancel","()V",false));
        il.add(new VarInsnNode(ALOAD,9));
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Set","remove","(Ljava/lang/Object;)Z",true));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ALOAD,11));
        il.add(new TypeInsnNode(NEW,"org/springframework/scheduling/config/CronTask"));
        il.add(new InsnNode(DUP));
        il.add(new VarInsnNode(ALOAD,17));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/CronTask","getRunnable","()Ljava/lang/Runnable;",false));
        il.add(new VarInsnNode(ALOAD,3));
        il.add(new MethodInsnNode(INVOKESPECIAL,"org/springframework/scheduling/config/CronTask","<init>","(Ljava/lang/Runnable;Ljava/lang/String;)V",false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/ScheduledTaskRegistrar","scheduleCronTask","(Lorg/springframework/scheduling/config/CronTask;)Lorg/springframework/scheduling/config/ScheduledTask;",false));
        il.add(new VarInsnNode(ASTORE,16));
        il.add(new VarInsnNode(ALOAD,9));
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Set","add","(Ljava/lang/Object;)Z",true));
        il.add(new InsnNode(POP));
        il.add(new InsnNode(ICONST_1));
        il.add(new VarInsnNode(ISTORE,4));
        il.add(new JumpInsnNode(GOTO,labelNode460));
        il.add(labelNode402);
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/ScheduledTask","cancel","()V",false));
        il.add(new VarInsnNode(ALOAD,9));
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Set","remove","(Ljava/lang/Object;)Z",true));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ALOAD,11));
        il.add(new TypeInsnNode(NEW,"org/springframework/scheduling/config/CronTask"));
        il.add(new InsnNode(DUP));
        il.add(new VarInsnNode(ALOAD,17));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/CronTask","getRunnable","()Ljava/lang/Runnable;",false));
        il.add(new VarInsnNode(ALOAD,17));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/CronTask","getExpression","()Ljava/lang/String;",false));
        il.add(new MethodInsnNode(INVOKESPECIAL,"org/springframework/scheduling/config/CronTask","<init>","(Ljava/lang/Runnable;Ljava/lang/String;)V",false));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"org/springframework/scheduling/config/ScheduledTaskRegistrar","scheduleCronTask","(Lorg/springframework/scheduling/config/CronTask;)Lorg/springframework/scheduling/config/ScheduledTask;",false));
        il.add(new VarInsnNode(ASTORE,16));
        il.add(new VarInsnNode(ALOAD,9));
        il.add(new VarInsnNode(ALOAD,16));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Set","add","(Ljava/lang/Object;)Z",true));
        il.add(new InsnNode(POP));
        il.add(new InsnNode(ICONST_1));
        il.add(new VarInsnNode(ISTORE,4));
        il.add(new JumpInsnNode(GOTO,labelNode460));
        il.add(labelNode457);
        il.add(new InsnNode(ICONST_0));
        il.add(new VarInsnNode(ISTORE,4));
        il.add(labelNode460);
        il.add(new JumpInsnNode(GOTO,labelNode147));
        il.add(labelNode463);
        il.add(new JumpInsnNode(GOTO,labelNode108));
        il.add(labelNode466);
        il.add(new VarInsnNode(ALOAD,8));
        il.add(new JumpInsnNode(IFNULL,labelNode505));
        il.add(new VarInsnNode(ALOAD,7));
        il.add(new VarInsnNode(ALOAD,8));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map","get","(Ljava/lang/Object;)Ljava/lang/Object;",true));
        il.add(new JumpInsnNode(IFNULL,labelNode505));
        il.add(new VarInsnNode(ALOAD,7));
        il.add(new VarInsnNode(ALOAD,8));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map","remove","(Ljava/lang/Object;)Ljava/lang/Object;",true));
        il.add(new InsnNode(POP));
        il.add(new VarInsnNode(ALOAD,7));
        il.add(new VarInsnNode(ALOAD,8));
        il.add(new VarInsnNode(ALOAD,9));
        il.add(new MethodInsnNode(INVOKEINTERFACE,"java/util/Map","putIfAbsent","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",true));
        il.add(new InsnNode(POP));
        il.add(labelNode505);
        il.add(new VarInsnNode(ALOAD,6));
        il.add(new VarInsnNode(ALOAD,5));
        il.add(new VarInsnNode(ALOAD,7));
        il.add(new MethodInsnNode(INVOKEVIRTUAL,"java/lang/reflect/Field","set","(Ljava/lang/Object;Ljava/lang/Object;)V",false));
        il.add(new VarInsnNode(ILOAD,4));
        il.add(new JumpInsnNode(IFEQ,labelNode522));
        il.add(new LdcInsnNode("Success!"));
        il.add(new InsnNode(ARETURN));
        il.add(labelNode522);
        il.add(new VarInsnNode(ALOAD,12));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_3;
        methodNode.maxLocals = IndexConstants.INDEX_17;
    }
}
