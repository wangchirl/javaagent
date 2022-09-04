package com.shadow.core.asm.loadtime;

import com.shadow.utils.Constants;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.Map;

public class SimpleJobAsmHandler extends AbstractAsmHandler {

    public SimpleJobAsmHandler(String innerClassName, Map<String, String> args) {
        super(innerClassName, args);
        System.out.println("ASM Simple Job agent ...");
    }

    @Override
    public void getMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new InsnNode(Opcodes.ACONST_NULL));
        il.add(new VarInsnNode(Opcodes.ASTORE, 2));
        il.add(new VarInsnNode(Opcodes.ALOAD, 1));
        il.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/shadow/supports/helper/ScheduleTaskInfoEnum", "getScheduleTaskBeanNameByTaskKey", "(Ljava/lang/String;)Ljava/lang/String;", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 3));
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        il.add(new FieldInsnNode(Opcodes.GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new VarInsnNode(Opcodes.ALOAD, 3));
        il.add(new LdcInsnNode(Type.getType("Lcom/shadow/supports/framework/ICronTriggerTask;")));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "com/shadow/supports/framework/ICronTriggerTask"));
        il.add(new VarInsnNode(Opcodes.ASTORE, 4));
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "run", "()V", true));
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "com/shadow/supports/framework/ICronTriggerTask", "getResult", "()Lcom/shadow/supports/framework/support/ScheduleResult;", true));
        il.add(new VarInsnNode(Opcodes.ASTORE, 2));
        il.add(new VarInsnNode(Opcodes.ALOAD, 2));
        il.add(new InsnNode(Opcodes.ARETURN));
        methodNode.maxStack = 3;
        methodNode.maxLocals = 5;
    }
}
