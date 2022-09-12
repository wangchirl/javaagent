package com.shadow.core.asm.handler;

import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class XxlJobAsmHandler extends AbstractAsmHandler {

    /**
     * 1、先看 ifxxx 的 label 有多少个
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
     * 29: ldc           #22                 // class com/xxl/job/core/executor/XxlJobExecutor
     * 31: invokeinterface #15,  2           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/Class;)Ljava/lang/Object;
     * 36: checkcast     #22                 // class com/xxl/job/core/executor/XxlJobExecutor
     * 39: astore        4
     * 41: ldc           #22                 // class com/xxl/job/core/executor/XxlJobExecutor
     * 43: ldc           #23                 // String jobHandlerRepository
     * 45: invokevirtual #24                 // Method java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
     * 48: astore        5
     * 50: aload         5
     * 52: iconst_1
     * 53: invokevirtual #25                 // Method java/lang/reflect/Field.setAccessible:(Z)V
     * 56: aload         5
     * 58: aload         4
     * 60: invokevirtual #26                 // Method java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
     * 63: astore        6
     * 65: aload         6
     * 67: checkcast     #27                 // class java/util/Map
     * 70: aload_1
     * 71: invokeinterface #28,  2           // InterfaceMethod java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
     * 76: checkcast     #29                 // class com/xxl/job/core/handler/IJobHandler
     * 79: astore        7
     * 81: aload         7
     * 83: invokevirtual #30                 // Method com/xxl/job/core/handler/IJobHandler.execute:()V
     * 86: ldc           #31                 // String Execute Xxl Job Successful !
     * 88: astore        8
     * 90: aload_2
     * 91: ifnonnull     98
     * 94: aload_3
     * 95: ifnull        104
     * 98: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 101: invokevirtual #32                 // Method java/lang/ThreadLocal.remove:()V
     * 104: aload         8
     * 106: areturn
     * 107: astore        9
     * 109: aload_2
     * 110: ifnonnull     117
     * 113: aload_3
     * 114: ifnull        123
     * 117: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 120: invokevirtual #32                 // Method java/lang/ThreadLocal.remove:()V
     * 123: aload         9
     * 125: athrow
     */
    @Override
    public void setThreadLocalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        // try - finally
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
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "com/xxl/job/core/executor/XxlJobExecutor"
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOBEXECUTOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor())));
        // "jobHandlerRepository"
        il.add(new LdcInsnNode(XxlConstants.JOB_HANDLERRE_POSITORY));
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
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        // "com/xxl/job/core/handler/IJobHandler"、"execute"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EXECUTE, BaseConstants.V_, false));
        il.add(new LdcInsnNode(CommonConstants.XXL_SUCCESS));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_8));
        il.add(end);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_2));
        il.add(new JumpInsnNode(IFNONNULL, line98));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_3));
        il.add(new JumpInsnNode(IFNULL, line104));
        il.add(line98);
        // "Ljava/lang/ThreadLocal;"
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
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
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor()));
        // "java/lang/ThreadLocal"、"remove"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false));
        il.add(line123);
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_9));
        il.add(new InsnNode(ATHROW));
        methodNode.maxStack = IndexConstants.INDEX_9;
        methodNode.maxLocals = IndexConstants.INDEX_10;
    }

    /**
     * 0: aload_0
     * 1: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 4: ldc           #22                 // class com/xxl/job/core/executor/XxlJobExecutor
     * 6: invokeinterface #15,  2           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/Class;)Ljava/lang/Object;
     * 11: checkcast     #22                 // class com/xxl/job/core/executor/XxlJobExecutor
     * 14: astore        4
     * 16: ldc           #22                 // class com/xxl/job/core/executor/XxlJobExecutor
     * 18: ldc           #23                 // String jobHandlerRepository
     * 20: invokevirtual #24                 // Method java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
     * 23: astore        5
     * 25: aload         5
     * 27: iconst_1
     * 28: invokevirtual #25                 // Method java/lang/reflect/Field.setAccessible:(Z)V
     * 31: aload         5
     * 33: aload         4
     * 35: invokevirtual #26                 // Method java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
     * 38: astore        6
     * 40: aload         6
     * 42: checkcast     #27                 // class java/util/Map
     * 45: aload_1
     * 46: invokeinterface #28,  2           // InterfaceMethod java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
     * 51: checkcast     #29                 // class com/xxl/job/core/handler/IJobHandler
     * 54: astore        7
     * 56: aload         7
     * 58: invokevirtual #30                 // Method com/xxl/job/core/handler/IJobHandler.execute:()V
     * 61: ldc           #31                 // String Execute Xxl Job Successful !
     * 63: areturn
     */
    @Override
    public void setNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_0));
        // "Lorg/springframework/context/ApplicationContext;"
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor()));
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor())));
        // "org/springframework/context/ApplicationContext"、"getBean"、"(Ljava/lang/Class;)Ljava/lang/Object;"
        il.add(new MethodInsnNode(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true));
        // "com/xxl/job/core/executor/XxlJobExecutor"
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOBEXECUTOR_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_4));
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        il.add(new LdcInsnNode(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor())));
        // "jobHandlerRepository"
        il.add(new LdcInsnNode(XxlConstants.JOB_HANDLERRE_POSITORY));
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
        il.add(new TypeInsnNode(CHECKCAST, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName()));
        il.add(new VarInsnNode(ASTORE, IndexConstants.INDEX_7));
        il.add(new VarInsnNode(ALOAD, IndexConstants.INDEX_7));
        // "com/xxl/job/core/handler/IJobHandler"、"execute"、"()V"
        il.add(new MethodInsnNode(INVOKEVIRTUAL, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EXECUTE, BaseConstants.V_, false));
        il.add(new LdcInsnNode(CommonConstants.XXL_SUCCESS));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = IndexConstants.INDEX_7;
        methodNode.maxLocals = IndexConstants.INDEX_8;
    }
}
