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

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.jar.asm.Opcodes.ACC_PRIVATE;
import static net.bytebuddy.jar.asm.Opcodes.ACC_PUBLIC;
import static net.bytebuddy.matcher.ElementMatchers.named;


public abstract class AbstractBuddyHandler extends AbstractHandler implements IBuddyHandler, AsmVisitorWrapper {

    /**
     * agent method body
     */
    private void setMethodBody(MethodVisitor methodVisitor) {
        if (getThreadLocalInnerClassName() != null &&
                getThreadLocalFieldName() != null) {
            setThreadLocalMethodBody(methodVisitor);
        } else {
            setNormalMethodBody(methodVisitor);
        }
    }

    /**
     * 可传递参数的方法体实现
     */
    public abstract void setThreadLocalMethodBody(MethodVisitor methodVisitor);

    /**
     * 常规方法体实现
     */
    public abstract void setNormalMethodBody(MethodVisitor methodVisitor);

    /**
     * inner class name
     */
    private String innerClassName;

    String getInnerClassName() {
        return innerClassName;
    }

    public void initInnerClassName() {
        this.innerClassName = getArgs().get(CommonConstants.CONTROLLER_CLASS).replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    }

    public void handle(Instrumentation inst) {
        new AgentBuilder.Default()
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
        // 1、访问 class
        classVisitor.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC,
                getInnerClassName(),
                null,
                BaseConstants.OBJECT_TYPE.getInternalName(),
                null
        );

        // 2、spring ioc 添加字段
        {
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
        // 2.1 hook 字段
        {
            addFields(classVisitor);
        }

        // 3、添加方法
        MethodVisitor methodVisitor = classVisitor.visitMethod(
                ACC_PUBLIC,
                getMethodName().get(),
                BaseConstants.O_SSO,
                null,
                new String[]{BaseConstants.EXCEPTION_TYPE.getDescriptor()}
        );
        {
            // 3.1 方法注解及参数注解 @RequestMapping
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
            // 4.1 hook 方法
            addMethods(classVisitor);
        }
        classVisitor.visitEnd();
        return classVisitor;
    }
}
