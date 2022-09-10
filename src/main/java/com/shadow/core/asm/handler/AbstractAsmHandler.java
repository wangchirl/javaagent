package com.shadow.core.asm.handler;

import com.shadow.core.AbstractHandler;
import com.shadow.utils.BaseConstants;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.FileUtils;
import com.shadow.utils.SpringConstants;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.tree.AnnotationNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.util.Map;

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

    AbstractAsmHandler(Map<String, String> args) {
        super(args);
        this.innerClassName = getArgs().get(CommonConstants.CONTROLLER_CLASS).replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    }

    AbstractAsmHandler(String innerClassName, Map<String, String> args) {
        super(args);
        this.innerClassName = innerClassName;
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
                new String[]{BaseConstants.EXCEPTION_DESCRIPTOR});
        // 1.2 方法注解及注解参数
        AnnotationNode annotation = (AnnotationNode) methodNode.visitAnnotation(SpringConstants.SPRING_REQUEST_MAPPING_DESCRIPTOR, true);
        // 数组格式的参数
        AnnotationVisitor value = annotation.visitArray(CommonConstants.SPRING_REQUEST_MAPPING_VALUE);
        value.visit(CommonConstants.SPRING_REQUEST_MAPPING_VALUE, getArgs().get(CommonConstants.HTTP_REQUEST_PREFIX_URI));
        // 1.3 方法参数注解及注解参数
        // @PathVariable("taskKey")
        AnnotationVisitor pathvariable = methodNode.visitParameterAnnotation(
                0,
                SpringConstants.SPRING_PATH_VARIABLE_DESCRIPTOR,
                true
        );
        pathvariable.visit(CommonConstants.SPRING_REQUEST_MAPPING_VALUE, CommonConstants.SPRING_PATH_VARIABLE_PARAMETER_NAME_TASK_KEY);
        // @RequestParam(value = "params", required = false)
        AnnotationVisitor requestparam = methodNode.visitParameterAnnotation(
                1,
                SpringConstants.SPRING_REQUEST_PARAM_DESCRIPTOR,
                true
        );
        requestparam.visit(CommonConstants.SPRING_REQUEST_PARAM_NAEM, CommonConstants.SPRING_REQUEST_PARAM_NAME);
        requestparam.visit(CommonConstants.SPRING_REQUEST_PARAM_REQUIRED, false);
        // @RequestBody(required = false)
        AnnotationVisitor requestbody = methodNode.visitParameterAnnotation(
                2,
                SpringConstants.SPRING_REQUEST_BODY_DESCRIPTOR,
                true
        );
        requestbody.visit(CommonConstants.SPRING_REQUEST_PARAM_REQUIRED, false);
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
                SpringConstants.SPRING_APPLICATION_CONTEXT_DESCRIPTOR,
                null,
                null);
        fieldNode.visitAnnotation(SpringConstants.SPRING_AUTOWIRED_DESCRIPTOR, true);
        return fieldNode;
    }

}
