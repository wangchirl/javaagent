package com.shadow.core.asm.handler;

import com.shadow.utils.Constants;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

import java.util.Map;


public class XxlJobAsmHandler extends AbstractAsmHandler {

    public XxlJobAsmHandler(Map<String, String> args) {
        super(args);
        if (isDebug()) {
            System.out.println("ASM Xxl Job Agent ...");
        }
    }

    public XxlJobAsmHandler(String innerClassName, Map<String, String> args) {
        super(innerClassName, args);
        if (isDebug()) {
            System.out.println("ASM Xxl Job Agent ...");
        }
    }

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
    public void getThreadLocalMethodBody(MethodNode methodNode) {
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
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new JumpInsnNode(IFNULL, line14));
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), "Ljava/lang/ThreadLocal;"));
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/ThreadLocal", "set", "(Ljava/lang/Object;)V", false));
        il.add(new JumpInsnNode(GOTO, line25));
        il.add(line14);
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new JumpInsnNode(IFNULL, line25));
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), "Ljava/lang/ThreadLocal;"));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/ThreadLocal", "set", "(Ljava/lang/Object;)V", false));
        il.add(line25);
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "com/xxl/job/core/executor/XxlJobExecutor"));
        il.add(new VarInsnNode(ASTORE, 4));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new LdcInsnNode("jobHandlerRepository"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false));
        il.add(new VarInsnNode(ASTORE, 5));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false));
        il.add(new VarInsnNode(ASTORE, 6));
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new TypeInsnNode(CHECKCAST, "java/util/Map"));
        il.add(new VarInsnNode(ALOAD, 1));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "com/xxl/job/core/handler/IJobHandler"));
        il.add(new VarInsnNode(ASTORE, 7));
        il.add(new VarInsnNode(ALOAD, 7));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "com/xxl/job/core/handler/IJobHandler", "execute", "()V", false));
        il.add(new LdcInsnNode(Constants.XXL_SUCCESS));
        il.add(new VarInsnNode(ASTORE, 8));
        il.add(end);
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new JumpInsnNode(IFNONNULL, line98));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new JumpInsnNode(IFNULL, line104));
        il.add(line98);
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), "Ljava/lang/ThreadLocal;"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/ThreadLocal", "remove", "()V", false));
        il.add(line104);
        il.add(new VarInsnNode(ALOAD, 8));
        il.add(new InsnNode(ARETURN));
        il.add(ret);
        il.add(new VarInsnNode(ASTORE, 9));
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new JumpInsnNode(IFNONNULL, line117));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new JumpInsnNode(IFNULL, line123));
        il.add(line117);
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), "Ljava/lang/ThreadLocal;"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/ThreadLocal", "remove", "()V", false));
        il.add(line123);
        il.add(new VarInsnNode(ALOAD, 9));
        il.add(new InsnNode(ATHROW));
        methodNode.maxStack = 9;
        methodNode.maxLocals = 10;
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
    public void getNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "com/xxl/job/core/executor/XxlJobExecutor"));
        il.add(new VarInsnNode(ASTORE, 4));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new LdcInsnNode("jobHandlerRepository"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false));
        il.add(new VarInsnNode(ASTORE, 5));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new InsnNode(ICONST_1));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false));
        il.add(new VarInsnNode(ASTORE, 6));
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new TypeInsnNode(CHECKCAST, "java/util/Map"));
        il.add(new VarInsnNode(ALOAD, 1));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "com/xxl/job/core/handler/IJobHandler"));
        il.add(new VarInsnNode(ASTORE, 7));
        il.add(new VarInsnNode(ALOAD, 7));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "com/xxl/job/core/handler/IJobHandler", "execute", "()V", false));
        il.add(new LdcInsnNode(Constants.XXL_SUCCESS));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = 7;
        methodNode.maxLocals = 8;
    }
}
