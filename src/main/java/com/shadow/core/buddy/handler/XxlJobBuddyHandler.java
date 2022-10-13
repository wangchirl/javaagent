package com.shadow.core.buddy.handler;


import com.shadow.utils.*;
import net.bytebuddy.jar.asm.*;

import static net.bytebuddy.jar.asm.Opcodes.*;

public class XxlJobBuddyHandler extends AbstractBuddyHandler {

    @Override
    public void setThreadLocalMethodBody(MethodVisitor methodVisitor) {
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, null);
        Label label3 = new Label();
        methodVisitor.visitTryCatchBlock(label2, label3, label2, null);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        Label label4 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label4);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false);
        Label label5 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitJumpInsn(IFNULL, label5);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SET, BaseConstants.V_O, false);
        methodVisitor.visitLabel(label5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitLdcInsn(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, XxlConstants.XXL_JOBEXECUTOR_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor()));
        methodVisitor.visitLdcInsn(XxlConstants.JOB_HANDLERRE_POSITORY);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.CLASS_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETDECLAREDFIELD, BaseConstants.REFLECT_FIELD_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SETACCESSIBLE, BaseConstants.V_Z, false);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitTypeInsn(CHECKCAST, BaseConstants.MAP_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, BaseConstants.MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, true);
        methodVisitor.visitTypeInsn(CHECKCAST, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_7);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_7);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EXECUTE, BaseConstants.V_, false);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        Label label6 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        Label label7 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label7);
        methodVisitor.visitLabel(label6);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor());
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false);
        methodVisitor.visitJumpInsn(GOTO, label7);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_8);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        Label label8 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label8);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        Label label9 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label9);
        methodVisitor.visitLabel(label8);
        methodVisitor.visitFieldInsn(GETSTATIC, getThreadLocalInnerClassName(), getThreadLocalFieldName(), BaseConstants.THREADLOCAL_TYPE.getDescriptor());
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.THREADLOCAL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_REMOVE, BaseConstants.V_, false);
        methodVisitor.visitLabel(label9);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_8);
        methodVisitor.visitInsn(ATHROW);
        methodVisitor.visitLabel(label7);
        methodVisitor.visitLdcInsn(CommonConstants.XXL_SUCCESS);
        methodVisitor.visitInsn(ARETURN);
    }

    @Override
    public void setNormalMethodBody(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitLdcInsn(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, XxlConstants.XXL_JOBEXECUTOR_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor()));
        methodVisitor.visitLdcInsn(XxlConstants.JOB_HANDLERRE_POSITORY);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.CLASS_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETDECLAREDFIELD, BaseConstants.REFLECT_FIELD_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SETACCESSIBLE, BaseConstants.V_Z, false);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitTypeInsn(CHECKCAST, BaseConstants.MAP_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, BaseConstants.MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, true);
        methodVisitor.visitTypeInsn(CHECKCAST, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_7);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_7);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, XxlConstants.XXL_JOBHANDLER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_EXECUTE, BaseConstants.V_, false);
        methodVisitor.visitLdcInsn(CommonConstants.XXL_SUCCESS);
        methodVisitor.visitInsn(ARETURN);
    }


    @Override
    public void setCrudMethodBody(MethodVisitor methodVisitor) {
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        // "Lorg/springframework/context/ApplicationContext;"
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        methodVisitor.visitLdcInsn(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor()));
        // "org/springframework/context/ApplicationContext" "getBean" "(Ljava/lang/Class;)Ljava/lang/Object;"
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true);
        // "com/xxl/job/core/executor/XxlJobExecutor"
        methodVisitor.visitTypeInsn(CHECKCAST, XxlConstants.XXL_JOBEXECUTOR_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        // "Lcom/xxl/job/core/executor/XxlJobExecutor;"
        methodVisitor.visitLdcInsn(Type.getType(XxlConstants.XXL_JOBEXECUTOR_TYPE.getDescriptor()));
        // "jobHandlerRepository"
        methodVisitor.visitLdcInsn(XxlConstants.JOB_HANDLERRE_POSITORY);
        // "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;"
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.CLASS_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETDECLAREDFIELD, BaseConstants.REFLECT_FIELD_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitInsn(ICONST_1);
        // "java/lang/reflect/Field", "setAccessible", "(Z)V"
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SETACCESSIBLE, BaseConstants.V_Z, false);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        // "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;"
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.REFLECT_FIELD_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, BaseConstants.O_O, false);
        // "java/util/Map"
        methodVisitor.visitTypeInsn(CHECKCAST, BaseConstants.MAP_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        // "java/util/Map", "keySet", "()Ljava/util/Set;"
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, BaseConstants.MAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_KEY_SET, BaseConstants.SET_, true);
        methodVisitor.visitInsn(ARETURN);
    }

}
