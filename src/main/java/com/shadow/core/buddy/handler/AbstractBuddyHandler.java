package com.shadow.core.buddy.handler;

import com.shadow.core.AbstractHandler;
import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.ClassReader;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.*;
import net.bytebuddy.pool.TypePool;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

import static net.bytebuddy.jar.asm.Opcodes.*;
import static net.bytebuddy.matcher.ElementMatchers.named;


public abstract class AbstractBuddyHandler extends AbstractHandler implements IBuddyHandler, AsmVisitorWrapper {

    /**
     * agent method body
     */
    public void setMethodBody(MethodVisitor methodVisitor) {
        if (getThreadLocalInnerClassName() != null &&
                getThreadLocalFieldName() != null) {
            if (originHandler == null) {
                setThreadLocalMethodBody(methodVisitor);
            } else {
                originHandler.setThreadLocalMethodBody(methodVisitor);
            }
        } else {
            if (originHandler == null) {
                setNormalMethodBody(methodVisitor);
            } else {
                originHandler.setNormalMethodBody(methodVisitor);
            }
        }
    }

    @Override
    protected void init() {
        initInnerClassName();
    }

    /**
     * thread local run method body
     */
    public abstract void setThreadLocalMethodBody(MethodVisitor methodVisitor);

    /**
     * normal run method body
     */
    public abstract void setNormalMethodBody(MethodVisitor methodVisitor);

    /**
     * inner class name
     */
    private String innerClassName;

    /**
     * origin handler for attach api
     */
    private AbstractBuddyHandler originHandler;

    public void initOriginHandler(AbstractBuddyHandler originHandler) {
        this.originHandler = originHandler;
    }

    public AbstractBuddyHandler getOriginHandler() {
        return originHandler;
    }

    String getInnerClassName() {
        return innerClassName;
    }

    public void initInnerClassName() {
        this.innerClassName = getArgs().get(CommonConstants.CONTROLLER_CLASS).replaceAll(CommonConstants.REG_DOT, CommonConstants.BIAS);
    }

    public ClassFileTransformer handle(Instrumentation inst) {
        return new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.REDEFINITION)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .type(named(getArgs().get(CommonConstants.CONTROLLER_CLASS)))
                .transform((builder, typeDescription, classLoader, module) -> {
                    DynamicType.Builder<?> newBuilder = builder.visit(this);
                    if (isDebug()) {
                        FileUtils.writeBytes(getInnerClassName() + CommonConstants.CLASS_SUFFIX, newBuilder.make().getBytes());
                    }
                    return newBuilder;
                }).installOn(inst);
    }

    @Override
    public int mergeWriter(int flags) {
        return ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS;
    }

    @Override
    public int mergeReader(int flags) {
        return ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
    }

    @Override
    public ClassVisitor wrap(TypeDescription instrumentedType,
                             ClassVisitor classVisitor,
                             Implementation.Context implementationContext,
                             TypePool typePool, FieldList<FieldDescription.InDefinedShape> fields,
                             MethodList<?> methods, int writerFlags, int readerFlags) {
        // 1、visit class
        classVisitor.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC,
                getInnerClassName(),
                null,
                BaseConstants.OBJECT_TYPE.getInternalName(),
                null
        );
        {
            // 2、spring ioc field
            FieldVisitor fieldVisitor = classVisitor.visitField(
                    ACC_PRIVATE,
                    getArgs().get(CommonConstants.IOC_FIELD_NAME),
                    SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor(),
                    null,
                    null
            );
            AnnotationVisitor annotationVisitor = fieldVisitor.visitAnnotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getDescriptor(), Boolean.TRUE);
            annotationVisitor.visit(CommonConstants.CONST_REQUIRED, Boolean.TRUE);
            annotationVisitor.visitEnd();
        }
        {
            // 2.1 add more fields
            addFields(classVisitor);
        }

        // 3、add run method
        MethodVisitor methodVisitor = classVisitor.visitMethod(
                ACC_PUBLIC,
                getMethodName().get(),
                BaseConstants.O_SSO,
                null,
                new String[]{/*BaseConstants.EXCEPTION_TYPE.getDescriptor()*/}
        );
        {
            // 3.1 method annotation
            // @RequestMapping
            AnnotationVisitor requestMapping = methodVisitor.visitAnnotation(SpringConstants.SPRING_REQUEST_MAPPING_TYPE.getDescriptor(), Boolean.TRUE);
            AnnotationVisitor value = requestMapping.visitArray(CommonConstants.CONST_VALUE);
            value.visit(null, getArgs().get(CommonConstants.HTTP_REQUEST_PREFIX_URI));
            value.visitEnd();
            requestMapping.visitEnd();
            // @PathVariable
            AnnotationVisitor pathVariable = methodVisitor.visitParameterAnnotation(IndexConstants.INDEX_0, SpringConstants.SPRING_PATH_VARIABLE_TYPE.getDescriptor(), Boolean.TRUE);
            pathVariable.visit(CommonConstants.CONST_VALUE, CommonConstants.CONST_TASK_KEY);
            pathVariable.visitEnd();
            // @RequestParam
            AnnotationVisitor requestParam = methodVisitor.visitParameterAnnotation(IndexConstants.INDEX_1, SpringConstants.SPRING_REQUEST_PARAM_TYPE.getDescriptor(), Boolean.TRUE);
            requestParam.visit(CommonConstants.CONST_NAME, CommonConstants.CONST_PARAMS);
            requestParam.visit(CommonConstants.CONST_REQUIRED, Boolean.FALSE);
            requestParam.visitEnd();
            // @RequestBody
            AnnotationVisitor requestBody = methodVisitor.visitParameterAnnotation(IndexConstants.INDEX_2, SpringConstants.SPRING_REQUEST_BODY_TYPE.getDescriptor(), Boolean.TRUE);
            requestBody.visit(CommonConstants.CONST_REQUIRED, Boolean.FALSE);
            requestBody.visitEnd();
        }
        {
            // 4、set body
            methodVisitor.visitCode();
            setMethodBody(methodVisitor);
            methodVisitor.visitMaxs(0, 0);
            methodVisitor.visitEnd();
        }
        {
            // 4.1 add more methods
            addMoreMethods(classVisitor);
        }
        classVisitor.visitEnd();
        return classVisitor;
    }

    private void addMoreMethods(ClassVisitor classVisitor) {
        // crud method
        addCurdMethod(classVisitor);
        // hook method
        addMethods(classVisitor);
    }

    private void addCurdMethod(ClassVisitor classVisitor) {
        // 1. add crud method
        MethodVisitor methodVisitor = classVisitor.visitMethod(
                jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC,
                CommonConstants.DEFAULT_CRUD_METHOD_NAME,
                BaseConstants.O_ISS,
                null,
                new String[]{/*BaseConstants.EXCEPTION_TYPE.getDescriptor()*/}
        );
        // 1.2 method annotation
        AnnotationVisitor annotation = methodVisitor.visitAnnotation(SpringConstants.SPRING_REQUEST_MAPPING_TYPE.getDescriptor(), true);
        // array value
        AnnotationVisitor value = annotation.visitArray(CommonConstants.CONST_VALUE);
        value.visit(null, CommonConstants.DEFAULT_CRUD_HTTP_PATH);
        value.visitEnd();
        annotation.visitEnd();
        // 1.3 method parameter annotation
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
        if (originHandler != null) {
            originHandler.setCrudMethodBody(methodVisitor);
        } else {
            setCrudMethodBody(methodVisitor);
        }
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }

    protected abstract void setCrudMethodBody(MethodVisitor methodVisitor);

}
