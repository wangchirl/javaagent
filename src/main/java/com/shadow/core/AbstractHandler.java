package com.shadow.core;

import com.shadow.utils.Constants;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public abstract class AbstractHandler {

    /**
     * agent method body
     *
     * @return {@link java.lang.String}
     */
    abstract Supplier<String> getMethodBody();

    private Map<String, String> args;

    Map<String, String> getArgs() {
        return this.args;
    }

    AbstractHandler(Map<String, String> args) {
        this.args = args;
    }

    private boolean isDebug() {
        return Boolean.parseBoolean(this.args.get(Constants.DEBUG));
    }

    /**
     * agent method name
     *
     * @return {@link java.lang.String}
     */
    private Supplier<String> getMethodName() {
        return () -> this.args.get(Constants.METHOD_NAME) == null ? this.getClass().getSimpleName().toLowerCase() : this.args.get(Constants.METHOD_NAME);
    }

    byte[] handle(ClassLoader loader, String className) {
        try {
            // 1、得到类池
            ClassPool cp = new ClassPool();
            // 2、设置类路径
            LoaderClassPath classPath = new LoaderClassPath(loader);
            cp.appendClassPath(classPath);
            // 3、得到需要操作的类
            CtClass cc = cp.get(className);
            // 4、给得到的类添加注解属性
            setClassField(cc);
            // 5、给得到的类添加方法
            CtMethod method = getAndSetClassMethod(cp, cc, getMethodName(), getMethodBody());
            // 6、给方法添加注解和参数
            ClassFile ccFile = cc.getClassFile();
            ConstPool constPool = ccFile.getConstPool();
            // 6.1 添加方法注解
            setClassMethodAnnotations(method, this.args.get(Constants.HTTP_REQUEST_PREFIX_URI), constPool);
            // 6.2 添加方法参数
            setClassMethodParameters(method, constPool);
            // 7、write file for debug
            if (this.isDebug()) {
                cc.writeFile();
            }
            // 8、remove classpath
            cp.removeClassPath(classPath);
            // 9、return new byte code
            return cc.toBytecode();
        } catch (Exception e) {
            System.out.println("handle " + this.getClass().getSimpleName() + " Job Agent error " + e.getMessage());
            e.printStackTrace();
        }
        // return null for do nothing
        return null;
    }

    private void setClassField(CtClass cc) throws CannotCompileException, NotFoundException {
        // 1、创建属性
        CtField ctField = CtField.make(Constants.SPRING_IOC_FIELD + this.args.get(Constants.IOC_FIELD_NAME) + Constants.SEMICOLON, cc);
        // 2、设置属性访问权限
        ctField.setModifiers(Modifier.PRIVATE);
        cc.addField(ctField);
        // 3、给属性添加注解
        ctField = cc.getDeclaredField(this.args.get(Constants.IOC_FIELD_NAME));
        List<AttributeInfo> attributes = ctField.getFieldInfo().getAttributes();
        AnnotationsAttribute annotationsAttr = !attributes.isEmpty() ? (AnnotationsAttribute) attributes.get(0) :
                new AnnotationsAttribute(ctField.getFieldInfo().getConstPool(), AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation(Constants.SPRING_AUTOWIRED, ctField.getFieldInfo().getConstPool());
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
        method.setBody(this.args.get(Constants.METHOD_BODY) == null ? methodBody.get() : this.args.get(Constants.METHOD_BODY));
        cc.addMethod(method);
        return method;
    }


    private void setClassMethodAnnotations(CtMethod method, String path, ConstPool constPool) {
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        // 1、创建方法注解
        Annotation annotation = new Annotation(Constants.SPRING_REQUEST_MAPPING, constPool);
        // 1.1 设置注解参数
        StringMemberValue path1 = new StringMemberValue(path, constPool);
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(constPool);
        arrayMemberValue.setValue(new MemberValue[]{path1});
        annotation.addMemberValue(Constants.SPRING_REQUEST_MAPPING_PATH, arrayMemberValue);
        // 2、添加到方法上
        methodAttr.addAnnotation(annotation);
        method.getMethodInfo().addAttribute(methodAttr);
    }

    private void setClassMethodParameters(CtMethod method, ConstPool constPool) {
        ParameterAnnotationsAttribute parameterAnnotationsAttr =
                new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
        // 1、创建方法参数
        // 1.1 方法参数1
        Annotation annotation1 = new Annotation(Constants.SPRING_PATH_VARIABLE, constPool);
        annotation1.addMemberValue(Constants.SPRING_REQUEST_MAPPING_VALUE, new StringMemberValue(Constants.SPRING_PATH_VARIABLE_PARAMETER_NAME, constPool));
        // 1.2 方法参数2
        Annotation annotation2 = new Annotation(Constants.SPRING_REQUEST_PARAM, constPool);
        annotation2.addMemberValue(Constants.SPRING_REQUEST_MAPPING_VALUE, new StringMemberValue(Constants.SPRING_REQUEST_PARAM_NAME, constPool));
        annotation2.addMemberValue(Constants.SPRING_REQUEST_PARAM_REQUIRED, new BooleanMemberValue(false, constPool));
        // 1.3 方法参数3
        Annotation annotation3 = new Annotation(Constants.SPRING_REQUEST_BODY, constPool);
        annotation3.addMemberValue(Constants.SPRING_REQUEST_PARAM_REQUIRED, new BooleanMemberValue(false, constPool));
        // 2、加入方法
        Annotation[][] annotations = new Annotation[3][1];
        annotations[0][0] = annotation1;
        annotations[1][0] = annotation2;
        annotations[2][0] = annotation3;
        parameterAnnotationsAttr.setAnnotations(annotations);
        method.getMethodInfo().addAttribute(parameterAnnotationsAttr);
    }

    String setThreadLocal() {
        if (this.args.get(Constants.THREADLOCAL_CLASS) != null && this.args.get(Constants.THREADLOCAL_FIELD_NAME) != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("try {");
            builder.append("\n    if($2 != null) {");
            builder.append(getArgs().get(Constants.THREADLOCAL_CLASS));
            builder.append(".");
            builder.append(getArgs().get(Constants.THREADLOCAL_FIELD_NAME));
            builder.append(".set($2);");
            builder.append("\n   } else {");
            builder.append(getArgs().get(Constants.THREADLOCAL_CLASS));
            builder.append(".");
            builder.append(getArgs().get(Constants.THREADLOCAL_FIELD_NAME));
            builder.append(".set($3);");
            builder.append("}");
            return builder.toString();
        }
        return "";
    }

    String removeThreadLocal() {
        if (this.args.get(Constants.THREADLOCAL_CLASS) != null && this.args.get(Constants.THREADLOCAL_FIELD_NAME) != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("} finally {");
            builder.append("if ($2 != null || $3 != null){");
            builder.append(getArgs().get(Constants.THREADLOCAL_CLASS));
            builder.append(".");
            builder.append(getArgs().get(Constants.THREADLOCAL_FIELD_NAME));
            builder.append(".remove();");
            builder.append("  }");
            builder.append("}");
            return builder.toString();
        }
        return "";
    }

}
