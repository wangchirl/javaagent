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
     * 可传递参数的方法体实现
     */
    public abstract void setThreadLocalMethodBody(MethodNode methodNode);

    /**
     * 常规方法体实现
     */
    public abstract void setNormalMethodBody(MethodNode methodNode);

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

    public byte[] handle(byte[] classfileBuffer) {
        try {
            // 1、读取 class buffer
            ClassReader cr = new ClassReader(classfileBuffer);
            // 2、从 class buffer -> class node
            // 默认 ASM5 ，支持 JDK8版本
            ClassNode cn = new ClassNode(CommonConstants.ASM_API_VERSION);
            cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            // 3、transform
            // 3.1 create Field
            FieldNode fieldNode = setClassField(CommonConstants.ASM_API_VERSION);
            // 3.2 add Filed
            cn.fields.add(fieldNode);
            // 3.3 hook method 添加额外的字段
            addFields(CommonConstants.ASM_API_VERSION, cn);
            // 3.4 create MethodNode
            MethodNode methodNode = getAndSetClassMethod(CommonConstants.ASM_API_VERSION);
            // 3.5 add to class
            cn.methods.add(methodNode);
            // 3.6 hook method 添加额外的方法
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

    /**
     * 添加方法、方法注解及参数注解
     * <p> @RequestMapping(path = {"/agent/run/{taskKey}"})
     * public Object xxljobjavassisthandler(@PathVariable("taskKey") String var1,@RequestParam(value = "params", required = false) String var2,@RequestBody(required = false) Object var3) throws Exception {
     * }
     * </p>
     */
    public MethodNode getAndSetClassMethod(int api) {
        // 1. 添加方法
        MethodNode methodNode = new MethodNode(
                api,
                Opcodes.ACC_PUBLIC,
                getMethodName().get(),
                BaseConstants.O_SSO,
                null,
                new String[]{BaseConstants.EXCEPTION_TYPE.getDescriptor()});
        // 1.2 方法注解及注解参数
        AnnotationNode annotation = (AnnotationNode) methodNode.visitAnnotation(SpringConstants.SPRING_REQUEST_MAPPING_TYPE.getDescriptor(), true);
        // 数组格式的参数
        AnnotationVisitor value = annotation.visitArray(CommonConstants.CONST_VALUE);
        value.visit(CommonConstants.CONST_VALUE, getArgs().get(CommonConstants.HTTP_REQUEST_PREFIX_URI));
        // 1.3 方法参数注解及注解参数
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

    /**
     * 3.1 添加字段及字段注解
     *
     * <p> @Autowired
     * private ApplicationContext $$$springIoc$$$
     */
    private FieldNode setClassField(int api) {
        FieldNode fieldNode = new FieldNode(
                api,
                Opcodes.ACC_PRIVATE,
                getArgs().get(CommonConstants.IOC_FIELD_NAME),
                SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getDescriptor(),
                null,
                null);
        fieldNode.visitAnnotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getDescriptor(), true);
        return fieldNode;
    }

}
