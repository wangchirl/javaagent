package com.shadow.core.asm.loadtime;

import com.shadow.utils.Constants;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.*;

import java.util.Map;

public class XxlJobAsmHandler extends AbstractAsmHandler {

    public XxlJobAsmHandler(String innerClassName, Map<String, String> args) {
        super(innerClassName, args);
        System.out.println("ASM Xxl Job Agent ...");
    }

    @Override
    public void getMethodBody(MethodNode methodNode) {
        InsnList il = methodNode.instructions;
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        il.add(new FieldInsnNode(Opcodes.GETFIELD, getInnerClassName(), getArgs().get(Constants.IOC_FIELD_NAME), "Lorg/springframework/context/ApplicationContext;"));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "org/springframework/context/ApplicationContext", "getBean", "(Ljava/lang/Class;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "com/xxl/job/core/executor/XxlJobExecutor"));
        il.add(new VarInsnNode(Opcodes.ASTORE, 2));
        il.add(new LdcInsnNode(Type.getType("Lcom/xxl/job/core/executor/XxlJobExecutor;")));
        il.add(new LdcInsnNode("jobHandlerRepository"));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 3));
        il.add(new VarInsnNode(Opcodes.ALOAD, 3));
        il.add(new InsnNode(Opcodes.ICONST_1));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false));
        il.add(new VarInsnNode(Opcodes.ALOAD, 3));
        il.add(new VarInsnNode(Opcodes.ALOAD, 2));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false));
        il.add(new VarInsnNode(Opcodes.ASTORE, 4));
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "java/util/Map"));
        il.add(new VarInsnNode(Opcodes.ALOAD, 1));
        il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true));
        il.add(new TypeInsnNode(Opcodes.CHECKCAST, "com/xxl/job/core/handler/IJobHandler"));
        il.add(new VarInsnNode(Opcodes.ASTORE, 5));
        il.add(new VarInsnNode(Opcodes.ALOAD, 5));
        il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "com/xxl/job/core/handler/IJobHandler", "execute", "()V", false));
        il.add(new LdcInsnNode("xxx"));
        il.add(new InsnNode(Opcodes.ARETURN));
        methodNode.maxStack = 2;
        methodNode.maxLocals = 6;
    }
}
