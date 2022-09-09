package com.shadow.core.asm.handler;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

import com.shadow.utils.Constants;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.Map;

public class SimpleJobAsmHandler extends AbstractAsmHandler {

    public SimpleJobAsmHandler(Map<String, String> args) {
        super(args);
        if (isDebug()) {
            System.out.println("ASM Simple Job agent ...");
        }
    }

    public SimpleJobAsmHandler(String innerClassName, Map<String, String> args) {
        super(innerClassName, args);
        if (isDebug()) {
            System.out.println("ASM Simple Job agent ...");
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
     * 25: aload_1
     * 26: invokestatic  #34                 // Method com/shadow/supports/helper/ScheduleTaskInfoEnum.getScheduleTaskBeanNameByTaskKey:(Ljava/lang/String;)Ljava/lang/String;
     * 29: astore        4
     * 31: aload_0
     * 32: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 35: aload         4
     * 37: ldc           #35                 // class com/shadow/supports/framework/ICronTriggerTask
     * 39: invokeinterface #36,  3           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
     * 44: checkcast     #35                 // class com/shadow/supports/framework/ICronTriggerTask
     * 47: astore        5
     * 49: aload         5
     * 51: invokeinterface #37,  1           // InterfaceMethod com/shadow/supports/framework/ICronTriggerTask.run:()V
     * 56: aload         5
     * 58: invokeinterface #38,  1           // InterfaceMethod com/shadow/supports/framework/ICronTriggerTask.getResult:()Lcom/shadow/supports/framework/support/ScheduleResult;
     * 63: astore        6
     * 65: aload_2
     * 66: ifnonnull     73
     * 69: aload_3
     * 70: ifnull        79
     * 73: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 76: invokevirtual #33                 // Method java/lang/ThreadLocal.remove:()V
     * 79: aload         6
     * 81: areturn
     * 82: astore        7
     * 84: aload_2
     * 85: ifnonnull     92
     * 88: aload_3
     * 89: ifnull        98
     * 92: getstatic     #20                 // Field com/shadow/supports/framework/ScheduleService.JOB_PARAMETERS_THREAD_LOCAL:Ljava/lang/ThreadLocal;
     * 95: invokevirtual #33                 // Method java/lang/ThreadLocal.remove:()V
     * 98: aload         7
     * 100: athrow
     */
    @Override
    public void getThreadLocalMethodBody(MethodNode methodNode) {
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
        il.add(new VarInsnNode(ALOAD, 1));
        il.add(new MethodInsnNode(INVOKESTATIC, "com/shadow/supports/helper/ScheduleTaskInfoEnum", "getScheduleTaskBeanNameByTaskKey", "(Ljava/lang/String;)Ljava/lang/String;", false));
        il.add(new VarInsnNode(ASTORE, 4));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new LdcInsnNode(Type.getType("Lcom/shadow/supports/framework/ICronTriggerTask;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask"));
        il.add(new VarInsnNode(ASTORE, 5));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "run", "()V", true));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "getResult", "()Lcom/shadow/supports/framework/support/ScheduleResult;", true));
        il.add(new VarInsnNode(ASTORE, 6));
        il.add(end);
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new JumpInsnNode(IFNONNULL, line73));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new JumpInsnNode(IFNULL, line79));
        il.add(line73);
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), "Ljava/lang/ThreadLocal;"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/ThreadLocal", "remove", "()V", false));
        il.add(line79);
        il.add(new VarInsnNode(ALOAD, 6));
        il.add(new InsnNode(ARETURN));
        il.add(ret);
        il.add(new VarInsnNode(ASTORE, 7));
        il.add(new VarInsnNode(ALOAD, 2));
        il.add(new JumpInsnNode(IFNONNULL, line92));
        il.add(new VarInsnNode(ALOAD, 3));
        il.add(new JumpInsnNode(IFNULL, line98));
        il.add(line92);
        il.add(new FieldInsnNode(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), "Ljava/lang/ThreadLocal;"));
        il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/ThreadLocal", "remove", "()V", false));
        il.add(line98);
        il.add(new VarInsnNode(ALOAD, 7));
        il.add(new InsnNode(ATHROW));
        methodNode.maxStack = 7;
        methodNode.maxLocals = 8;
    }

    /**
     * 0: aload_1
     * 1: invokestatic  #34                 // Method com/shadow/supports/helper/ScheduleTaskInfoEnum.getScheduleTaskBeanNameByTaskKey:(Ljava/lang/String;)Ljava/lang/String;
     * 4: astore        4
     * 6: aload_0
     * 7: getfield      #8                  // Field applicationContext:Lorg/springframework/context/ApplicationContext;
     * 10: aload         4
     * 12: ldc           #35                 // class com/shadow/supports/framework/ICronTriggerTask
     * 14: invokeinterface #36,  3           // InterfaceMethod org/springframework/context/ApplicationContext.getBean:(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
     * 19: checkcast     #35                 // class com/shadow/supports/framework/ICronTriggerTask
     * 22: astore        5
     * 24: aload         5
     * 26: invokeinterface #37,  1           // InterfaceMethod com/shadow/supports/framework/ICronTriggerTask.run:()V
     * 31: aload         5
     * 33: invokeinterface #38,  1           // InterfaceMethod com/shadow/supports/framework/ICronTriggerTask.getResult:()Lcom/shadow/supports/framework/support/ScheduleResult;
     * 38: areturn
     */
    @Override
    public void getNormalMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(ALOAD, 1));
        il.add(new MethodInsnNode(INVOKESTATIC, "com/shadow/supports/helper/ScheduleTaskInfoEnum", "getScheduleTaskBeanNameByTaskKey", "(Ljava/lang/String;)Ljava/lang/String;", false));
        il.add(new VarInsnNode(ASTORE, 4));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new FieldInsnNode(GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(ALOAD, 4));
        il.add(new LdcInsnNode(Type.getType("Lcom/shadow/supports/framework/ICronTriggerTask;")));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask"));
        il.add(new VarInsnNode(ASTORE, 5));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "run", "()V", true));
        il.add(new VarInsnNode(ALOAD, 5));
        il.add(new MethodInsnNode(INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "getResult", "()Lcom/shadow/supports/framework/support/ScheduleResult;", true));
        il.add(new InsnNode(ARETURN));
        methodNode.maxStack = 5;
        methodNode.maxLocals = 6;
    }

    @Override
    public void addFields(int api, ClassNode cn) throws Exception {

    }

    @Override
    public void addMethods(int api, ClassNode cn) throws Exception {

    }
}
