package com.shadow.core.buddy.handler;

import com.shadow.core.AbstractHandler;
import com.shadow.utils.*;
import jdk.internal.org.objectweb.asm.ClassReader;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
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
        this.innerClassName = getArgs().get(CommonConstants.CONTROLLER_CLASS).replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
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
        // 1、访问 class
        classVisitor.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC,
                getInnerClassName(),
                null,
                BaseConstants.OBJECT_TYPE.getInternalName(),
                null
        );
        System.out.println("测试：" + originHandler);
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

    public class TestVisitor extends ClassVisitor {

        private String owner;

        public TestVisitor(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);
            this.owner = name;
        }

        @Override
        public MethodVisitor visitMethod(int access,
                                         String name,
                                         String descriptor,
                                         String signature,
                                         String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
            if (mv != null && getMethodName().get().equals(name) && BaseConstants.O_SSO.equals(descriptor)) {
                boolean isAbstractMethod = (access & Opcodes.ACC_ABSTRACT) != 0;
                boolean isNativeMethod = (access & Opcodes.ACC_NATIVE) != 0;
                if (!isAbstractMethod && !isNativeMethod) {
                    System.out.println("新旧方法体");
                    generateNewBody(mv, owner, access, name, descriptor);
                    return null;
                }
            }
            return mv;
        }

        protected void generateNewBody(MethodVisitor mv, String owner, int methodAccess, String methodName, String methodDesc) {
            // (1) method argument types and return type
            Type t = Type.getType(methodDesc);
            Type[] argumentTypes = t.getArgumentTypes();
            Type returnType = t.getReturnType();


            // (2) compute the size of local variable and operand stack
            boolean isStaticMethod = ((methodAccess & Opcodes.ACC_STATIC) != 0);
            int localSize = isStaticMethod ? 0 : 1;
            for (Type argType : argumentTypes) {
                localSize += argType.getSize();
            }
            int stackSize = returnType.getSize();


            // (3) method body
            mv.visitCode();
            if (returnType.getSort() == Type.VOID) {
                mv.visitInsn(RETURN);
            } else if (returnType.getSort() >= Type.BOOLEAN && returnType.getSort() <= Type.INT) {
                mv.visitInsn(ICONST_1);
                mv.visitInsn(IRETURN);
            } else if (returnType.getSort() == Type.LONG) {
                mv.visitInsn(LCONST_0);
                mv.visitInsn(LRETURN);
            } else if (returnType.getSort() == Type.FLOAT) {
                mv.visitInsn(FCONST_0);
                mv.visitInsn(FRETURN);
            } else if (returnType.getSort() == Type.DOUBLE) {
                mv.visitInsn(DCONST_0);
                mv.visitInsn(DRETURN);
            } else {
                mv.visitInsn(ACONST_NULL);
                mv.visitInsn(ARETURN);
            }
            mv.visitMaxs(stackSize, localSize);
            mv.visitEnd();
        }
    }
}
