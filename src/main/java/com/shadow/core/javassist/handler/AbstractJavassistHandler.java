package com.shadow.core.javassist.handler;

import com.shadow.core.AbstractHandler;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.SpringConstants;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.*;

import java.util.List;
import java.util.function.Supplier;


public abstract class AbstractJavassistHandler extends AbstractHandler implements IJavassistHandler {

    /**
     * agent method body
     *
     * @return {@link java.lang.String}
     */
    public abstract Supplier<String> getMethodBody();

    /**
     * 主方法
     */
    public byte[] handle(String className) {
        try {
            // 1、得到类池
            ClassPool cp = new ClassPool();
            // 2、设置类路径
            ClassLoader classLoader = cp.getClassLoader();
            Class<?> springContext = classLoader.loadClass(SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getClassName());
            cp.insertClassPath(new ClassClassPath(springContext));
            Class<?> springAutowired = classLoader.loadClass(SpringConstants.SPRING_AUTOWIRED_TYPE.getClassName());
            cp.insertClassPath(new ClassClassPath(springAutowired));
            cp.appendClassPath(new ClassClassPath(this.getClass()));
            // 3、得到需要操作的类
            CtClass cc = cp.get(className);
            // 4、给得到的类添加注解属性
            setClassField(cc);
            // 4.1 扩展额外的字段
            addFields(cc);
            // 5、给得到的类添加方法
            addRunMethod(cp, cc);
            if (getArgs().getCrud()) {
                addCrudMethod(cp, cc);
            }
            // 6.3 扩展添加其他方法
            addMethods(cp, cc);
            // 7、write file for debug
            if (this.isDebug()) {
                cc.writeFile();
            }
            // 8、return new byte code
            return cc.toBytecode();
        } catch (Exception e) {
            System.out.println("Javassist handle agent " + this.getClass().getSimpleName() + " Job Agent error " + e.getMessage());
            e.printStackTrace();
        }
        // return null for do nothing
        return null;
    }

    private void addRunMethod(ClassPool cp, CtClass cc) throws CannotCompileException, NotFoundException {
        CtMethod method = getAndSetClassMethod(cp, cc, getMethodName(), getMethodBody());
        // 6、给方法添加注解和参数
        ClassFile ccFile = cc.getClassFile();
        ConstPool constPool = ccFile.getConstPool();
        // 6.1 添加方法注解
        setClassMethodAnnotations(method, getArgs().getHttpUri(), constPool);
        // 6.2 添加方法参数
        setClassMethodParameters(method, constPool);
    }

    private void addCrudMethod(ClassPool cp, CtClass cc) throws CannotCompileException, NotFoundException {
        CtMethod method = getAndSetCrudClassMethod(cp, cc, () -> CommonConstants.DEFAULT_CRUD_METHOD_NAME, getCrudMethodBody());
        // 6、给方法添加注解和参数
        ClassFile ccFile = cc.getClassFile();
        ConstPool constPool = ccFile.getConstPool();
        // 6.1 添加方法注解
        setClassMethodAnnotations(method, CommonConstants.DEFAULT_CRUD_HTTP_PATH, constPool);
        // 6.2 添加方法参数
        setCrudClassMethodParameters(method, constPool);
    }

    public abstract Supplier<String> getCrudMethodBody();

    /**
     * 设置属性 & 属性添加注解
     */
    private void setClassField(CtClass cc) throws CannotCompileException, NotFoundException {
        // 1、创建属性
        CtField ctField = CtField.make(CommonConstants.ACC_PRIVATE + CommonConstants.SPACE + SpringConstants.SPRING_APPLICATION_CONTEXT_TYPE.getClassName() + CommonConstants.SPACE + getArgs().getIocFieldName() + CommonConstants.SEMICOLON, cc);
        // 2、设置属性访问权限
        ctField.setModifiers(Modifier.PRIVATE);
        cc.addField(ctField);
        // 3、给属性添加注解
        ctField = cc.getDeclaredField(getArgs().getIocFieldName());
        List<AttributeInfo> attributes = ctField.getFieldInfo().getAttributes();
        AnnotationsAttribute annotationsAttr = !attributes.isEmpty() ? (AnnotationsAttribute) attributes.get(0) :
                new AnnotationsAttribute(ctField.getFieldInfo().getConstPool(), AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getClassName(), ctField.getFieldInfo().getConstPool());
        annotationsAttr.addAnnotation(annotation);
        ctField.getFieldInfo().addAttribute(annotationsAttr);
    }


    private CtMethod getAndSetClassMethod(ClassPool cp, CtClass cc, Supplier<String> methodName, Supplier<String> methodBody) throws CannotCompileException, NotFoundException {
        CtClass string = cp.get(String.class.getName());
        CtClass object = cp.get(Object.class.getName());
        // 1、创建方法
        CtMethod method = new CtMethod(object, methodName.get(), new CtClass[]{string, string, object}, cc);
        // 2、方法访问权限
        method.setModifiers(Modifier.PUBLIC);
        method.setExceptionTypes(new CtClass[]{cp.get(Exception.class.getName())});
        // 3、设置方法体
        method.setBody(getArgs().getMethodBody() == null ? methodBody.get() : getArgs().getMethodBody());
        cc.addMethod(method);
        return method;
    }

    private CtMethod getAndSetCrudClassMethod(ClassPool cp, CtClass cc, Supplier<String> methodName, Supplier<String> methodBody) throws CannotCompileException, NotFoundException {
        CtClass string = cp.get(String.class.getName());
        CtClass object = cp.get(Object.class.getName());
        CtClass intClass = cp.get(int.class.getName());
        // 1、创建方法
        CtMethod method = new CtMethod(object, methodName.get(), new CtClass[]{intClass, string, string}, cc);
        // 2、方法访问权限
        method.setModifiers(Modifier.PUBLIC);
        method.setExceptionTypes(new CtClass[]{cp.get(Exception.class.getName())});
        // 3、设置方法体
        method.setBody(getArgs().getCrudMethodBody() == null ? methodBody.get() : getArgs().getCrudMethodBody());
        cc.addMethod(method);
        return method;
    }

    /**
     * 方法添加注解
     */
    private void setClassMethodAnnotations(CtMethod method, String path, ConstPool constPool) {
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        // 1、创建方法注解
        Annotation annotation = new Annotation(SpringConstants.SPRING_REQUEST_MAPPING_TYPE.getClassName(), constPool);
        // 1.1 设置注解参数
        StringMemberValue path1 = new StringMemberValue(path, constPool);
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(constPool);
        arrayMemberValue.setValue(new MemberValue[]{path1});
        annotation.addMemberValue(CommonConstants.CONST_PATH, arrayMemberValue);
        // 2、添加到方法上
        methodAttr.addAnnotation(annotation);
        method.getMethodInfo().addAttribute(methodAttr);
    }

    private void setClassMethodParameters(CtMethod method, ConstPool constPool) {
        ParameterAnnotationsAttribute parameterAnnotationsAttr =
                new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
        // 1、创建方法参数
        // 1.1 方法参数1
        Annotation annotation1 = new Annotation(SpringConstants.SPRING_PATH_VARIABLE_TYPE.getClassName(), constPool);
        annotation1.addMemberValue(CommonConstants.CONST_VALUE, new StringMemberValue(CommonConstants.CONST_TASK_KEY, constPool));
        // 1.2 方法参数2
        Annotation annotation2 = new Annotation(SpringConstants.SPRING_REQUEST_PARAM_TYPE.getClassName(), constPool);
        annotation2.addMemberValue(CommonConstants.CONST_VALUE, new StringMemberValue(CommonConstants.CONST_PARAMS, constPool));
        annotation2.addMemberValue(CommonConstants.CONST_REQUIRED, new BooleanMemberValue(false, constPool));
        // 1.3 方法参数3
        Annotation annotation3 = new Annotation(SpringConstants.SPRING_REQUEST_BODY_TYPE.getClassName(), constPool);
        annotation3.addMemberValue(CommonConstants.CONST_REQUIRED, new BooleanMemberValue(false, constPool));
        // 2、加入方法
        Annotation[][] annotations = new Annotation[3][1];
        annotations[0][0] = annotation1;
        annotations[1][0] = annotation2;
        annotations[2][0] = annotation3;
        parameterAnnotationsAttr.setAnnotations(annotations);
        method.getMethodInfo().addAttribute(parameterAnnotationsAttr);
    }

    private void setCrudClassMethodParameters(CtMethod method, ConstPool constPool) {
        ParameterAnnotationsAttribute parameterAnnotationsAttr =
                new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
        // 1、创建方法参数
        // 1.1 方法参数1
        Annotation annotation1 = new Annotation(SpringConstants.SPRING_PATH_VARIABLE_TYPE.getClassName(), constPool);
        annotation1.addMemberValue(CommonConstants.CONST_VALUE, new StringMemberValue(CommonConstants.CONST_OPERATION, constPool));
        // 1.2 方法参数2
        Annotation annotation2 = new Annotation(SpringConstants.SPRING_PATH_VARIABLE_TYPE.getClassName(), constPool);
        annotation2.addMemberValue(CommonConstants.CONST_VALUE, new StringMemberValue(CommonConstants.CONST_TASK_KEY, constPool));
        // 1.3 方法参数3
        Annotation annotation3 = new Annotation(SpringConstants.SPRING_REQUEST_PARAM_TYPE.getClassName(), constPool);
        annotation3.addMemberValue(CommonConstants.CONST_VALUE, new StringMemberValue(CommonConstants.CONST_CRON, constPool));
        annotation3.addMemberValue(CommonConstants.CONST_REQUIRED, new BooleanMemberValue(false, constPool));
        // 2、加入方法
        Annotation[][] annotations = new Annotation[3][1];
        annotations[0][0] = annotation1;
        annotations[1][0] = annotation2;
        annotations[2][0] = annotation3;
        parameterAnnotationsAttr.setAnnotations(annotations);
        method.getMethodInfo().addAttribute(parameterAnnotationsAttr);
    }

    String setThreadLocal() {
        if (getThreadLocalClassName() != null && getThreadLocalFieldName() != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("try {");
            builder.append("\n    if($2 != null) {");
            builder.append(getThreadLocalClassName());
            builder.append(".");
            builder.append(getThreadLocalFieldName());
            builder.append(".set($2);");
            builder.append("\n   } else {");
            builder.append(getThreadLocalClassName());
            builder.append(".");
            builder.append(getThreadLocalFieldName());
            builder.append(".set($3);");
            builder.append("}");
            return builder.toString();
        }
        return "";
    }

    /**
     * ThreadLocal 参数移除
     */
    String removeThreadLocal() {
        if (getThreadLocalClassName() != null && getThreadLocalFieldName() != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("} finally {");
            builder.append("if ($2 != null || $3 != null){");
            builder.append(getThreadLocalClassName());
            builder.append(".");
            builder.append(getThreadLocalFieldName());
            builder.append(".remove();");
            builder.append("  }");
            builder.append("}");
            return builder.toString();
        }
        return "";
    }

}
