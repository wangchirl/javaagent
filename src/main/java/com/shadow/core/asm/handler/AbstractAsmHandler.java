package com.shadow.core.asm.handler;

import com.shadow.core.AbstractHandler;
import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.tree.AnnotationNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;


public abstract class AbstractAsmHandler extends AbstractHandler implements IAsmHandler {

    /**
     * agent method body
     */
    private void setMethodBody(MethodNode methodNode) {
        if (getThreadLocalInnerClassName() != null &&
                getThreadLocalFieldName() != null) {
            setThreadLocalMethodBody(methodNode);
        } else {
            setNormalMethodBody(methodNode);
        }
    }

    /**
     * thread local run method
     */
    public abstract void setThreadLocalMethodBody(MethodNode methodNode);

    /**
     * normal run method
     */
    public abstract void setNormalMethodBody(MethodNode methodNode);

    public byte[] handle(byte[] classfileBuffer) {
        try {
            // 1、read class buffer
            ClassReader cr = new ClassReader(classfileBuffer);
            // 2、class buffer -> class node
            // asm api version
            ClassNode cn = new ClassNode(CommonConstants.ASM_API_VERSION);
            cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            // 3、transform
            // 3.1 create Field
            FieldNode fieldNode = setClassField(CommonConstants.ASM_API_VERSION);
            // 3.2 add Filed
            cn.fields.add(fieldNode);
            // 3.3 hook and add more fields
            addFields(CommonConstants.ASM_API_VERSION, cn);
            // 3.4 create run MethodNode
            MethodNode runMethodNode = getAndSetClassMethod(CommonConstants.ASM_API_VERSION);
            // 3.5 add to class
            cn.methods.add(runMethodNode);
            // 3.6 create crud MethodNode
            // add crud method
            MethodNode crudMethodNode = getAndSetCrudClassMethod(CommonConstants.ASM_API_VERSION);
            // 3.7 add to Class
            cn.methods.add(crudMethodNode);
            // 3.8 hook and add more methods
            addMethods(CommonConstants.ASM_API_VERSION, cn);
            // 4、class node -> class writer
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cn.accept(cw);
            // 5、debug
            if (isDebug()) {
                FileUtils.writeBytes(getInnerClassName() + CommonConstants.CLASS_SUFFIX, cw.toByteArray());
            }
            // 6、write to bytes
            return cw.toByteArray();
        } catch (Exception e) {
            System.out.println("ASM handle agent " + this.getClass().getSimpleName() + " Job Agent error " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public MethodNode getAndSetCrudClassMethod(int api) {
        // 1. add crud method
        MethodNode methodNode = new MethodNode(
                api,
                Opcodes.ACC_PUBLIC,
                CommonConstants.DEFAULT_CRUD_METHOD_NAME,
                BaseConstants.O_ISS,
                null,
                new String[]{/*BaseConstants.EXCEPTION_TYPE.getDescriptor()*/});
        // 1.2 method annotation
        AnnotationNode annotation = (AnnotationNode) methodNode.visitAnnotation(SpringConstants.SPRING_REQUEST_MAPPING_TYPE.getDescriptor(), true);
        // array value
        AnnotationVisitor value = annotation.visitArray(CommonConstants.CONST_VALUE);
        value.visit(CommonConstants.CONST_VALUE, CommonConstants.DEFAULT_CRUD_HTTP_PATH);
        // 1.3 method parameter annotation
        // @PathVariable("operation")
        AnnotationVisitor pathvariable1 = methodNode.visitParameterAnnotation(
                IndexConstants.INDEX_0,
                SpringConstants.SPRING_PATH_VARIABLE_TYPE.getDescriptor(),
                true
        );
        pathvariable1.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_OPERATION);
        // @PathVariable("taskKey")
        AnnotationVisitor pathvariable2 = methodNode.visitParameterAnnotation(
                IndexConstants.INDEX_1,
                SpringConstants.SPRING_PATH_VARIABLE_TYPE.getDescriptor(),
                true
        );
        pathvariable2.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_TASK_KEY);
        // @RequestParam(value = "cron", required = false)
        AnnotationVisitor requestParams = methodNode.visitParameterAnnotation(
                IndexConstants.INDEX_2,
                SpringConstants.SPRING_REQUEST_PARAM_TYPE.getDescriptor(),
                true
        );
        requestParams.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_CRON);
        requestParams.visit(CommonConstants.CONST_REQUIRED, false);
        // 1.4 set method body
        setCrudMethodBody(methodNode);
        return methodNode;
    }

    protected abstract void setCrudMethodBody(MethodNode methodNode);


    public MethodNode getAndSetClassMethod(int api) {
        // 1. add run method
        MethodNode methodNode = new MethodNode(
                api,
                Opcodes.ACC_PUBLIC,
                getMethodName().get(),
                BaseConstants.O_SSO,
                null,
                new String[]{/*BaseConstants.EXCEPTION_TYPE.getDescriptor()*/});
        // 1.2 method annotation
        AnnotationNode annotation = (AnnotationNode) methodNode.visitAnnotation(SpringConstants.SPRING_REQUEST_MAPPING_TYPE.getDescriptor(), true);
        // 数组格式的参数
        AnnotationVisitor value = annotation.visitArray(CommonConstants.CONST_VALUE);
        value.visit(CommonConstants.CONST_VALUE, getArgs().getHttpUri());
        // 1.3 method parameter annotation
        // @PathVariable("taskKey")
        AnnotationVisitor pathvariable = methodNode.visitParameterAnnotation(
                IndexConstants.INDEX_0,
                SpringConstants.SPRING_PATH_VARIABLE_TYPE.getDescriptor(),
                true
        );
        pathvariable.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_TASK_KEY);
        // @RequestParam(value = "params", required = false)
        AnnotationVisitor requestparam = methodNode.visitParameterAnnotation(
                IndexConstants.INDEX_1,
                SpringConstants.SPRING_REQUEST_PARAM_TYPE.getDescriptor(),
                true
        );
        requestparam.visit(CommonConstants.CONST_NAME, CommonConstants.CONST_PARAMS);
        requestparam.visit(CommonConstants.CONST_REQUIRED, false);
        // @RequestBody(required = false)
        AnnotationVisitor requestbody = methodNode.visitParameterAnnotation(
                IndexConstants.INDEX_2,
                SpringConstants.SPRING_REQUEST_BODY_TYPE.getDescriptor(),
                true
        );
        requestbody.visit(CommonConstants.CONST_REQUIRED, false);
        // 1.4 set method body
        setMethodBody(methodNode);
        return methodNode;
    }

    private FieldNode setClassField(int api) {
        FieldNode fieldNode = new FieldNode(
                api,
                Opcodes.ACC_PRIVATE,
                getArgs().getIocFieldName(),
                SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor(),
                null,
                null);
        fieldNode.visitAnnotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getDescriptor(), true);
        return fieldNode;
    }

}
