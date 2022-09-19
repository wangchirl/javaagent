package com.shadow.core.buddy.handler;

import static net.bytebuddy.jar.asm.Opcodes.*;

import com.shadow.utils.*;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;

public class SimpleJobBuddyHandler extends AbstractBuddyHandler {

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
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULETASKINFOENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKBEANNAMEBYTASKKEY, BaseConstants.S_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(Type.getType(SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, true);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRESULT, SimpleConstants.SCHEDULERESULT_, true);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
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
        methodVisitor.visitLabel(label7);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_7);
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
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_7);
        methodVisitor.visitInsn(ATHROW);
    }

    @Override
    public void setNormalMethodBody(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULETASKINFOENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKBEANNAMEBYTASKKEY, BaseConstants.S_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(Type.getType(SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RUN, BaseConstants.V_, true);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETRESULT, SimpleConstants.SCHEDULERESULT_, true);
        methodVisitor.visitInsn(ARETURN);
    }
    
}
