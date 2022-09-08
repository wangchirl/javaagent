package com.shadow.core.asm.handler;

import com.shadow.utils.Constants;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.Map;

public class SpringJobAsmHandler extends AbstractAsmHandler {

    public SpringJobAsmHandler(Map<String, String> args) {
        super(args);
        if (isDebug()) {
            System.out.println("ASM Spring Job agent ...");
        }
    }

    public SpringJobAsmHandler(String innerClassName, Map<String, String> args) {
        super(innerClassName, args);
        if (isDebug()) {
            System.out.println("ASM Spring Job agent ...");
        }
    }

    @Override
    public void getMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        il.add(new FieldInsnNode(Opcodes.GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lorg/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor;")));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor"));
        il.add(new VarInsnNode(Opcodes.ASTORE, 2));
        il.add(new VarInsnNode(Opcodes.ALOAD, 2));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/springframework/scheduling/annotation/ScheduledAnnotationBeanPostProcessor", "getScheduledTasks", "()Ljava/util/Set;", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 3));
        il.add(new VarInsnNode(Opcodes.ALOAD, 3));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true));
        il.add(new VarInsnNode(Opcodes.ASTORE, 4));
        LabelNode labelNode1 = new LabelNode();
        LabelNode labelNode2 = new LabelNode();
        LabelNode labelNode3 = new LabelNode();
        LabelNode labelNode4 = new LabelNode();
        il.add(labelNode1);
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true));
        il.add(new JumpInsnNode(Opcodes.IFEQ, labelNode4));
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "org/springframework/scheduling/config/ScheduledTask"));
        il.add(new VarInsnNode(Opcodes.ASTORE, 5));
        il.add(new VarInsnNode(Opcodes.ALOAD, 5));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/springframework/scheduling/config/ScheduledTask", "getTask", "()Lorg/springframework/scheduling/config/Task;", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 6));
        il.add(new VarInsnNode(Opcodes.ALOAD, 6));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/springframework/scheduling/config/Task", "getRunnable", "()Ljava/lang/Runnable;", false));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "org/springframework/scheduling/support/ScheduledMethodRunnable"));
        il.add(new VarInsnNode(Opcodes.ASTORE, 7));
        il.add(new VarInsnNode(Opcodes.ALOAD, 7));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/springframework/scheduling/support/ScheduledMethodRunnable", "getMethod", "()Ljava/lang/reflect/Method;", false));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/reflect/Method", "getName", "()Ljava/lang/String;", false));
        il.add(new VarInsnNode(Opcodes.ALOAD, 1));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false));

        il.add(new JumpInsnNode(Opcodes.IFEQ, labelNode3));
        il.add(new VarInsnNode(Opcodes.ALOAD, 7));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/springframework/scheduling/support/ScheduledMethodRunnable", "run", "()V", false));
        il.add(labelNode2);
        il.add(new JumpInsnNode(Opcodes.GOTO, labelNode4));
        il.add(labelNode3);
        il.add(new JumpInsnNode(Opcodes.GOTO, labelNode1));
        il.add(labelNode4);
        il.add(new LdcInsnNode(Constants.SUCCESS));
        il.add(new InsnNode(Opcodes.ARETURN));
        methodNode.maxStack = 7;
        methodNode.maxLocals = 8;
    }
}
