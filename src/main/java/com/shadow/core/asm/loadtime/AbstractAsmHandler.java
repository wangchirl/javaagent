package com.shadow.core.asm.loadtime;

import com.shadow.utils.Constants;
import com.shadow.utils.FileUtils;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.tree.AnnotationNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractAsmHandler implements IHandler {

    /**
     * agent method body
     */
    public abstract void getMethodBody(MethodNode methodNode);

    /**
     * 记录 javaagent 传递的参数信息
     */
    private Map<String, String> args;

    /**
     * inner class name
     */
    private String innerClassName;

    String getInnerClassName() {
        return innerClassName;
    }

    Map<String, String> getArgs() {
        return this.args;
    }

    AbstractAsmHandler(String innerClassName, Map<String, String> args) {
        this.innerClassName = innerClassName;
        this.args = args;
    }

    /**
     * 是否 debug 模式
     */
    private boolean isDebug() {
        return Boolean.parseBoolean(this.args.get(Constants.DEBUG));
    }

    /**
     * agent method name
     *
     * @return {@link java.lang.String}
     */
    public Supplier<String> getMethodName() {
        return () -> this.args.get(Constants.METHOD_NAME) == null ? this.getClass().getSimpleName().toLowerCase() : this.args.get(Constants.METHOD_NAME);
    }

    byte[] handle(byte[] classfileBuffer) {
        try {
            // 1、读取 class buffer
            ClassReader cr = new ClassReader(classfileBuffer);
            // 2、从 class buffer -> class node
            // 默认 ASM5 ，支持 JDK8版本
            int api = Opcodes.ASM5;
            ClassNode cn = new ClassNode(api);
            cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            // 3、transform
            // 3.1 Field
            setClassField(api, cn);
            // 3.2 添加额外的字段
            addFields(api, cn);
            // 3.3 Method
            getAndSetClassMethod(api, cn);
            // 3.4 添加额外的方法
            addMethods(api, cn);
            // 4、class node -> class writer
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cn.accept(cw);
            // 5、debug
            if (isDebug()) {
                FileUtils.writeBytes(getInnerClassName() + Constants.CLASS_SUFFIX, cw.toByteArray());
            }
            // 6、write to bytes
            return cw.toByteArray();
        } catch (Exception e) {
            System.out.println("ASM handle agent " + this.getClass().getSimpleName() + " Job Agent error " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private void getAndSetClassMethod(int api, ClassNode cn) {
        // 1. 添加方法
        MethodNode methodNode = new MethodNode(
                api,
                Opcodes.ACC_PUBLIC,
                getMethodName().get(),
                Constants.METHOD_DESCRIPTOR,
                null,
                new String[]{Constants.EXCEPTION_DESCRIPTOR});
        // 1.2 方法注解及注解参数
        AnnotationNode annotation = (AnnotationNode) methodNode.visitAnnotation(Constants.SPRING_REQUESTMAPPING_DESCRIPTOR, true);
        // 数组格式的参数
        AnnotationVisitor value = annotation.visitArray(Constants.SPRING_REQUEST_MAPPING_PATH);
        value.visit(Constants.SPRING_REQUEST_MAPPING_VALUE, getArgs().get(Constants.HTTP_REQUEST_PREFIX_URI));
        // 1.3 方法参数注解及注解参数
        // @PathVariable("taskKey")
        AnnotationVisitor pathvariable = methodNode.visitParameterAnnotation(
                0,
                Constants.SPRING_PATHVARIABLE_DESCRIPTOR,
                true
        );
        pathvariable.visit(Constants.SPRING_REQUEST_MAPPING_VALUE, Constants.SPRING_PATH_VARIABLE_PARAMETER_NAME_TASK_KEY);
        // @RequestParam(value = "params", required = false)
        AnnotationVisitor requestparam = methodNode.visitParameterAnnotation(
                1,
                Constants.SPRING_REQUESTPARAM_DESCRIPTOR,
                true
        );
        requestparam.visit(Constants.SPRING_REQUEST_PARAM_NAEM, Constants.SPRING_REQUEST_PARAM_NAME);
        requestparam.visit(Constants.SPRING_REQUEST_PARAM_REQUIRED, false);
        // @RequestBody(required = false)
        AnnotationVisitor requestbody = methodNode.visitParameterAnnotation(
                2,
                Constants.SPRING_REQUESTBODY_DESCRIPTOR,
                true
        );
        requestbody.visit(Constants.SPRING_REQUEST_PARAM_REQUIRED, false);
        // 1.4 方法体
        getMethodBody(methodNode);
        cn.methods.add(methodNode);
    }

    private void setClassField(int api, ClassNode cn) {
        // 3.1 添加字段及字段注解
        FieldNode fieldNode = new FieldNode(
                api,
                Opcodes.ACC_PRIVATE,
                this.args.get(Constants.IOC_FIELD_NAME),
                Constants.SPRING_APPLICATIONCONTEXT_DESCRIPTOR,
                null,
                null);
        fieldNode.visitAnnotation(Constants.SPRING_AUTOWIRED_DESCRIPTOR, true);
        cn.fields.add(fieldNode);
    }

}
