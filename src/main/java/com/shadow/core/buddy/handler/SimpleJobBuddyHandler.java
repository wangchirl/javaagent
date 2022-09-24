package com.shadow.core.buddy.handler;

import static net.bytebuddy.jar.asm.Opcodes.*;

import com.shadow.utils.*;
import net.bytebuddy.jar.asm.*;

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

    @Override
    public void addFields(ClassVisitor classVisitor) {
        FieldVisitor visitField = classVisitor.visitField(
                ACC_PRIVATE,
                getArgs().get(CommonConstants.SIMPLE_JOB_IOC_FIELD_NAME),
                SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getDescriptor(),
                null,
                null
        );
        visitField.visitAnnotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getDescriptor(), true);
        visitField.visitEnd();
    }

    @Override
    public void addMethods(ClassVisitor classVisitor) {
        // 1. 添加方法
        MethodVisitor methodVisitor = classVisitor.visitMethod(
                jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC,
                CommonConstants.DEFAULT_CRUD_METHOD_NAME,
                BaseConstants.O_ISS,
                null,
                new String[]{BaseConstants.EXCEPTION_TYPE.getDescriptor()}
        );
        // 1.2 方法注解及注解参数
        AnnotationVisitor annotation = methodVisitor.visitAnnotation(SpringConstants.SPRING_REQUEST_MAPPING_TYPE.getDescriptor(), true);
        // 数组格式的参数
        AnnotationVisitor value = annotation.visitArray(CommonConstants.CONST_VALUE);
        value.visit(null, CommonConstants.DEFAULT_CRUD_HTTP_PATH);
        value.visitEnd();
        annotation.visitEnd();
        // 1.3 方法参数注解及注解参数
        // @PathVariable("operation")
        AnnotationVisitor pathvariable1 = methodVisitor.visitParameterAnnotation(
                IndexConstants.INDEX_0,
                SpringConstants.SPRING_PATH_VARIABLE_TYPE.getDescriptor(),
                true
        );
        pathvariable1.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_OPERATION);
        pathvariable1.visitEnd();
        // @PathVariable("taskKey")
        AnnotationVisitor pathvariable2 = methodVisitor.visitParameterAnnotation(
                IndexConstants.INDEX_1,
                SpringConstants.SPRING_PATH_VARIABLE_TYPE.getDescriptor(),
                true
        );
        pathvariable2.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_TASK_KEY);
        pathvariable2.visitEnd();
        // @RequestParam(value = "cron", required = false)
        AnnotationVisitor requestParams = methodVisitor.visitParameterAnnotation(
                IndexConstants.INDEX_2,
                SpringConstants.SPRING_REQUEST_PARAM_TYPE.getDescriptor(),
                true
        );
        requestParams.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_CRON);
        requestParams.visit(CommonConstants.CONST_REQUIRED, false);
        requestParams.visitEnd();
        // 1.4 set method body
        methodVisitor.visitCode();
        setCrudMethodBody(methodVisitor);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }

    private void setCrudMethodBody(MethodVisitor methodVisitor) {
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitVarInsn(ILOAD, IndexConstants.INDEX_1);
        methodVisitor.visitTableSwitchInsn(IndexConstants.INDEX_1, IndexConstants.INDEX_4, label4, label0, label1, label2, label3);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.SIMPLE_JOB_IOC_FIELD_NAME), SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_UPDATE, BaseConstants.BOOLEAN_S_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        Label label5 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.SIMPLE_JOB_IOC_FIELD_NAME), SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_RESTART, BaseConstants.BOOLEAN_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.SIMPLE_JOB_IOC_FIELD_NAME), SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_CANCEL, BaseConstants.BOOLEAN_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULETASKINFOENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKBEANNAMEBYTASKKEY, BaseConstants.S_S, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_5);
        methodVisitor.visitLdcInsn(Type.getType(SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getDescriptor()));
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETBEAN, BaseConstants.O_S_C_, true);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitTypeInsn(CHECKCAST, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.IOC_FIELD_NAME), SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor());
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETENVIRONMENT, SpringConstants.ENVIRONMENT_, true);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, SimpleConstants.SIMPLE_SCHEDULETASKINFOENUM_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETSCHEDULETASKCRONNAMEBYTASKKEY, BaseConstants.S_S, false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SpringConstants.SPRING_ENVIRONMENT_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GETPROPERTY, BaseConstants.S_S, true);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SETTRIGGER, SimpleConstants.CRONTRIGGER_S, true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        Label label6 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, BaseConstants.STRING_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_LENGTH, BaseConstants.I_, false);
        methodVisitor.visitIntInsn(BIPUSH, IndexConstants.INDEX_6);
        methodVisitor.visitJumpInsn(IF_ICMPLE, label6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitTypeInsn(CHECKCAST, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_3);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_SETTRIGGER, SimpleConstants.CRONTRIGGER_S, true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label6);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.SIMPLE_JOB_IOC_FIELD_NAME), SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_6);
        methodVisitor.visitTypeInsn(CHECKCAST, SimpleConstants.SIMPLE_ICRONTRIGGERTASK_TYPE.getInternalName());
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_ADD, SimpleConstants.BOOLEAN_ICRONTRIGGERTASK, false);
        methodVisitor.visitVarInsn(ASTORE, IndexConstants.INDEX_4);
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_0);
        methodVisitor.visitFieldInsn(GETFIELD, getInnerClassName(), getArgs().get(CommonConstants.SIMPLE_JOB_IOC_FIELD_NAME), SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getDescriptor());
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, SimpleConstants.SIMPLE_COMMONSCHEDULINGCONFIGURER_TYPE.getInternalName(), MethodNameConstants.METHOD_NAME_GET, SimpleConstants.SCHEDULEVO_S, false);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label5);
        methodVisitor.visitVarInsn(ALOAD, IndexConstants.INDEX_4);
        methodVisitor.visitInsn(ARETURN);
    }
}
