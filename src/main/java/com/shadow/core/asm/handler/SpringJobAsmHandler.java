package com.shadow.core.asm.handler;

import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.Map;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class SpringJobAsmHandler extends AbstractAsmHandler {

    public SpringJobAsmHandler(Map<String, String> args) {
        super(args);
        if (isDebug()) {
            System.out.println(SpringConstants.ASM_PROXY_LOG_TIPS);
        }
    }

    public SpringJobAsmHandler(String innerClassName, Map<String, String> args) {
        super(innerClassName, args);
        if (isDebug()) {
            System.out.println(SpringConstants.ASM_PROXY_LOG_TIPS);
        }
    }

    /**
     * 0: aload_2
     * 1: ifnull        14
     * 4: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 7: aload_2
     * 8: invokevirtual #21                 // Method java/lang/ThreadLocal.set:(Ljava/lang/Object;)V
     * 11: goto          25
     * 14: aload_3
     * 15: ifnull        25
     * 18: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 21: aload_3
     * 22: invokevirtual #21                 // Method java/lang/ThreadLocal.set:(Ljava/lang/Object;)V
     * 25: aload_0
     * 26: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 29: ldc           #38                 // class org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor
     * 31: invokeinterface #15,  2           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/Class;)Ljava/lang/Object;
     * 36: checkcast     #38                 // class org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor
     * 39: astore        4
     * 41: aload         4
     * 43: invokevirtual #39                 // Method org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor.getScheduledTasks:()Ljava/util/Set;
     * 46: astore        5
     * 48: aload         5
     * 50: invokeinterface #40,  1           // InterfaceMethod java/util/Set.iterator:()Ljava/util/Iterator;
     * 55: astore        6
     * 57: aload         6
     * 59: invokeinterface #41,  1           // InterfaceMethod java/util/Iterator.hasNext:()Z
     * 64: ifeq          122
     * 67: aload         6
     * 69: invokeinterface #42,  1           // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
     * 74: checkcast     #43                 // class org/springframework/scheduling/config/ScheduledTask
     * 77: astore        7
     * 79: aload         7
     * 81: invokevirtual #44                 // Method org/springframework/scheduling/config/ScheduledTask.getTask:()Lorg/springframework/scheduling/config/Task;
     * 84: astore        8
     * 86: aload         8
     * 88: invokevirtual #45                 // Method org/springframework/scheduling/config/Task.getRunnable:()Ljava/lang/Runnable;
     * 91: checkcast     #46                 // class org/springframework/scheduling/support/ScheduledMethodRunnable
     * 94: astore        9
     * 96: aload         9
     * 98: invokevirtual #47                 // Method org/springframework/scheduling/support/ScheduledMethodRunnable.getMethod:()Ljava/lang/reflect/Method;
     * 101: invokevirtual #48                 // Method java/lang/reflect/Method.getName:()Ljava/lang/String;
     * 104: aload_1
     * 105: invokevirtual #49                 // Method java/lang/String.equals:(Ljava/lang/Object;)Z
     * 108: ifeq          119
     * 111: aload         9
     * 113: invokevirtual #50                 // Method org/springframework/scheduling/support/ScheduledMethodRunnable.run:()V
     * 116: goto          122
     * 119: goto          57
     * 122: ldc           #51                 // String Execute Spring Job Successful !
     * 124: astore        6
     * 126: aload_2
     * 127: ifnonnull     134
     * 130: aload_3
     * 131: ifnull        140
     * 134: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 137: invokevirtual #32                 // Method java/lang/ThreadLocal.remove:()V
     * 140: aload         6
     * 142: areturn
     * 143: astore        10
     * 145: aload_2
     * 146: ifnonnull     153
     * 149: aload_3
     * 150: ifnull        159
     * 153: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 156: invokevirtual #32                 // Method java/lang/ThreadLocal.remove:()V
     * 159: aload         10
     * 161: athrow
     */
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
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        // "java/lang/ThreadLocal"、"set"、"(Ljava/lang/Object;)V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false));
        il.add(new JumpInsnNode(GOTO, line25));
        il.add(line14);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line25));
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor()));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        // "java/lang/ThreadLocal"、"set"、"(Ljava/lang/Object;)V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false));
        il.add(line25);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;"
        il.add(new LdcInsnNode(Type.getType(SpringConstants.SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_4));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"、"getScheduledTasks"、"()Ljava/util/Set;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULEDTASKS, BaseConstants.SET_, false));
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
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_SCHEDULEDTASK_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        // "org/springframework/scheduling/config/ScheduledTask"、"getTask"、"()Lorg/springframework/scheduling/config/Task;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETTASK, SpringConstants.TASK_, false));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_8));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_8));
        // "org/springframework/scheduling/config/Task"、"getRunnable"、"()Ljava/lang/Runnable;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRUNNABLE, BaseConstants.RUNNABLE_, false));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"
        il.add(new TypeInsnNode(CHECKCAST, SpringConstants.SPRING_SCHEDULEDMETHODRUNNABLE_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_9));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"getMethod"、"()Ljava/lang/reflect/Method;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDMETHODRUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETMETHOD, BaseConstants.REFLECT_METHOD_, false));
        // "java/lang/reflect/Method"、"getName"、"()Ljava/lang/String;"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.REFLECT_METHOD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETNAME, BaseConstants.S_, false));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_1));
        // "java/lang/String"、"equals"、"(Ljava/lang/Object;)Z"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.STRING_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EQUALS, BaseConstants.Z_O, false));
        il.add(new JumpInsnNode(IFEQ, line119));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"run"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDMETHODRUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, false));
        il.add(new JumpInsnNode(GOTO, line122));
        il.add(line119);
        il.add(new JumpInsnNode(GOTO, line57));
        il.add(line122);
        il.add(new LdcInsnNode(CommonConstants.SPRING_SUCCESS));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_6));
        il.add(end);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line134));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line140));
        il.add(line134);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
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
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line159);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_10));
        il.add(new InsnNode(ATHROW));
        methodNode.maxStack = IndexConstants.INDEX_10;
        methodNode.maxLocals = IndexConstants.INDEX_11;

    }

    /**
     * 0: aload_0
     * 1: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 4: ldc           #39                 // class org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor
     * 6: invokeinterface #15,  2           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/Class;)Ljava/lang/Object;
     * 11: checkcast     #39                 // class org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor
     * 14: astore        4
     * 16: aload         4
     * 18: invokevirtual #40                 // Method org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor.getScheduledTasks:()Ljava/util/Set;
     * 21: astore        5
     * 23: aload         5
     * 25: invokeinterface #41,  1           // InterfaceMethod java/util/Set.iterator:()Ljava/util/Iterator;
     * 30: astore        6
     * 32: aload         6
     * 34: invokeinterface #42,  1           // InterfaceMethod java/util/Iterator.hasNext:()Z
     * 39: ifeq          97
     * 42: aload         6
     * 44: invokeinterface #43,  1           // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
     * 49: checkcast     #44                 // class org/springframework/scheduling/config/ScheduledTask
     * 52: astore        7
     * 54: aload         7
     * 56: invokevirtual #45                 // Method org/springframework/scheduling/config/ScheduledTask.getTask:()Lorg/springframework/scheduling/config/Task;
     * 59: astore        8
     * 61: aload         8
     * 63: invokevirtual #46                 // Method org/springframework/scheduling/config/Task.getRunnable:()Ljava/lang/Runnable;
     * 66: checkcast     #47                 // class org/springframework/scheduling/support/ScheduledMethodRunnable
     * 69: astore        9
     * 71: aload         9
     * 73: invokevirtual #48                 // Method org/springframework/scheduling/support/ScheduledMethodRunnable.getMethod:()Ljava/lang/reflect/Method;
     * 76: invokevirtual #49                 // Method java/lang/reflect/Method.getName:()Ljava/lang/String;
     * 79: aload_1
     * 80: invokevirtual #50                 // Method java/lang/String.equals:(Ljava/lang/Object;)Z
     * 83: ifeq          94
     * 86: aload         9
     * 88: invokevirtual #51                 // Method org/springframework/scheduling/support/ScheduledMethodRunnable.run:()V
     * 91: goto          97
     * 94: goto          32
     * 97: ldc           #52                 // String Execute Spring Job Successful !
     * 99: areturn
     */
    @Override
    public void setNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;

        LabelNode line32 = new LabelNode();
        LabelNode line94 = new LabelNode();
        LabelNode line97 = new LabelNode();

        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(Opcodes.GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;"
        il.add(new LdcInsnNode(Type.getType(SpringConstants.SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, SpringConstants.SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_4));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_4));
        // "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"、"getScheduledTasks"、"()Ljava/util/Set;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDANNOTATIONBEANPOSTPROCESSOR_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULEDTASKS, BaseConstants.SET_, false));
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
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, SpringConstants.SPRING_SCHEDULEDTASK_TYPE.getInternalName()));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_7));
        // "org/springframework/scheduling/config/ScheduledTask"、"getTask"、"()Lorg/springframework/scheduling/config/Task;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETTASK, SpringConstants.TASK_, false));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_8));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_8));
        // "org/springframework/scheduling/config/Task"、"getRunnable"、"()Ljava/lang/Runnable;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_TASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRUNNABLE, BaseConstants.RUNNABLE_, false));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, SpringConstants.SPRING_SCHEDULEDMETHODRUNNABLE_TYPE.getInternalName()));
        il.add(new VarInsnNode(Opcodes.ASTORE, IndexConstants.INDEX_9));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"getMethod"、"()Ljava/lang/reflect/Method;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDMETHODRUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETMETHOD, BaseConstants.REFLECT_METHOD_, false));
        // "java/lang/reflect/Method"、"getName"、"()Ljava/lang/String;"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, BaseConstants.REFLECT_METHOD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETNAME, BaseConstants.S_, false));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_1));
        // "java/lang/String"、"equals"、"(Ljava/lang/Object;)Z"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, BaseConstants.STRING_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EQUALS, BaseConstants.Z_O, false));
        il.add(new JumpInsnNode(Opcodes.IFEQ, line94));
        il.add(new VarInsnNode(Opcodes.ALOAD, IndexConstants.INDEX_9));
        // "org/springframework/scheduling/support/ScheduledMethodRunnable"、"run"、"()V"
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, SpringConstants.SPRING_SCHEDULEDMETHODRUNNABLE_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, false));
        il.add(new JumpInsnNode(Opcodes.GOTO, line97));
        il.add(line94);
        il.add(new JumpInsnNode(Opcodes.GOTO, line32));
        il.add(line97);
        il.add(new LdcInsnNode(CommonConstants.SPRING_SUCCESS));
        il.add(new InsnNode(Opcodes.ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_9;
        methodNode.maxLocals = IndexConstants.INDEX_10;
    }
}
