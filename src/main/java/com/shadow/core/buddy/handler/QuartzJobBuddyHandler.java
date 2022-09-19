package com.shadow.core.buddy.handler;

import static net.bytebuddy.jar.asm.Opcodes.*;

import com.shadow.utils.*;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;

public class QuartzJobBuddyHandler extends AbstractBuddyHandler {

    @Override
    public void setThreadLocalMethodBody(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(NEW, QuartzConstants.QUARTZ_JOBDATAMAP_TYPE.getInternalName());
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, QuartzConstants.QUARTZ_JOBDATAMAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_INIT, BaseConstants.V_, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(CommonConstants.CONST_PARAMS);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, QuartzConstants.QUARTZ_JOBDATAMAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_PUT, BaseConstants.V_SS, false);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitLdcInsn(CommonConstants.CONST_BODY);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, QuartzConstants.QUARTZ_JOBDATAMAP_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_PUT, BaseConstants.O_SO, false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S, true);
        methodVisitor.visitTypeInsn(CHECKCAST, QuartzConstants.QUARTZ_CRONTRIGGERIMPL_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitTypeInsn(NEW, QuartzConstants.QUARTZ_JOBKEY_TYPE.getInternalName());
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, QuartzConstants.QUARTZ_CRONTRIGGERIMPL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETJOBNAME, BaseConstants.S_, false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, QuartzConstants.QUARTZ_JOBKEY_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_INIT, BaseConstants.V_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitLdcInsn(Type.getType(SpringConstants.SPRING_QUARTZ_SCHEDULERFACTORYBEAN_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, SpringConstants.SPRING_QUARTZ_SCHEDULERFACTORYBEAN_TYPE.getInternalName());
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, SpringConstants.SPRING_QUARTZ_SCHEDULERFACTORYBEAN_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULER, QuartzConstants.SCHEDULER_, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_7);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_7);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, QuartzConstants.QUARTZ_SCHEDULER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_TRIGGERJOB, QuartzConstants.V_JOBKEY_JOBDATAMAP, true);
        methodVisitor.visitLdcInsn(CommonConstants.QUARTZ_SUCCESS);
        methodVisitor.visitInsn(ARETURN);
    }

    @Override
    public void setNormalMethodBody(MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_1);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S, true);
        methodVisitor.visitTypeInsn(CHECKCAST, QuartzConstants.QUARTZ_CRONTRIGGERIMPL_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitTypeInsn(NEW, QuartzConstants.QUARTZ_JOBKEY_TYPE.getInternalName());
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, QuartzConstants.QUARTZ_CRONTRIGGERIMPL_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETJOBNAME, BaseConstants.S_, false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, QuartzConstants.QUARTZ_JOBKEY_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_INIT, BaseConstants.V_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitLdcInsn(Type.getType(SpringConstants.SPRING_QUARTZ_SCHEDULERFACTORYBEAN_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_C_, true);
        methodVisitor.visitTypeInsn(CHECKCAST, SpringConstants.SPRING_QUARTZ_SCHEDULERFACTORYBEAN_TYPE.getInternalName());
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, SpringConstants.SPRING_QUARTZ_SCHEDULERFACTORYBEAN_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULER, QuartzConstants.SCHEDULER_, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, QuartzConstants.QUARTZ_SCHEDULER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_TRIGGERJOB, QuartzConstants.V_JOBKEY, true);
        methodVisitor.visitLdcInsn(CommonConstants.QUARTZ_SUCCESS);
        methodVisitor.visitInsn(ARETURN);
    }
}
