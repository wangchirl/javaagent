package com.shadow.core;

import com.shadow.utils.Constants;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.*;

import java.util.List;
import java.util.function.Supplier;


public abstract class AbstractHandler {

    private boolean debug = true;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * agent method name
     *
     * @return {@link java.lang.String}
     */
    Supplier<String> getMethodName() {
        return () -> this.getClass().getSimpleName().toLowerCase();
    }

    /**
     * request path
     *
     * @return {@link java.lang.String}
     */
    Supplier<String> getPath() {
        return () -> Constants.DEFAULT_PATH;
    }

    /**
     * agent method body
     *
     * @return {@link java.lang.String}
     */
    abstract Supplier<String> getMethodBody();

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
            setClassMethodAnnotations(method, getPath(), constPool);
            // 6.2 添加方法参数
            setClassMethodParameters(method, constPool);
            // 7、write file for debug
            if (debug) {
                cc.writeFile();
            }
            // 8、remove classpath
            cp.removeClassPath(classPath);
            // 9、return new bytecode
            return cc.toBytecode();
        } catch (Exception e) {
            System.out.println("handle Xxl Job Agent error " + e.getMessage());
            e.printStackTrace();
        }
        // return null for do nothing
        return null;
    }

    private void setClassField(CtClass cc) throws CannotCompileException, NotFoundException {
        // 1、创建属性
        CtField ctField = CtField.make("private org.springframework.context.ApplicationContext agentApplicationContext;", cc);
        // 2、设置属性访问权限
        ctField.setModifiers(Modifier.PRIVATE);
        cc.addField(ctField);
        // 3、给属性添加注解
        ctField = cc.getDeclaredField("agentApplicationContext" + Constants.DOLLER_D);
        List<AttributeInfo> attributes = ctField.getFieldInfo().getAttributes();
        AnnotationsAttribute annotationsAttr = !attributes.isEmpty() ? (AnnotationsAttribute) attributes.get(0) :
                new AnnotationsAttribute(ctField.getFieldInfo().getConstPool(), AnnotationsAttribute.invisibleTag);
        Annotation annotation = new Annotation("org.springframework.beans.factory.annotation.Autowired", ctField.getFieldInfo().getConstPool());
        annotationsAttr.addAnnotation(annotation);
        ctField.getFieldInfo().addAttribute(annotationsAttr);
    }


    private CtMethod getAndSetClassMethod(ClassPool cp, CtClass cc, Supplier<String> methodName, Supplier<String> supplier) throws CannotCompileException, NotFoundException {
        CtClass string = cp.get(String.class.getName());
        CtClass object = cp.get(Object.class.getName());
        // 1、创建方法
        CtMethod method = new CtMethod(object, methodName.get(), new CtClass[]{string, string, object}, cc);
        // 2、方法访问权限
        method.setModifiers(Modifier.PUBLIC);
        method.setExceptionTypes(new CtClass[]{cp.get(Exception.class.getName())});
        // 3、设置方法体
        method.setBody(supplier.get());
        cc.addMethod(method);
        return method;
    }


    private void setClassMethodAnnotations(CtMethod method, Supplier<String> path, ConstPool constPool) {
        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.invisibleTag);
        // 1、创建方法注解
        Annotation annotation = new Annotation("org.springframework.web.bind.annotation.RequestMapping", constPool);
        // 1.1 设置注解参数
        StringMemberValue path1 = new StringMemberValue(path.get(), constPool);
        ArrayMemberValue arrayMemberValue = new ArrayMemberValue(constPool);
        arrayMemberValue.setValue(new MemberValue[]{path1});
        annotation.addMemberValue("path", arrayMemberValue);
        // 2、添加到方法上
        methodAttr.addAnnotation(annotation);
        method.getMethodInfo().addAttribute(methodAttr);
    }

    private void setClassMethodParameters(CtMethod method, ConstPool constPool) {
        ParameterAnnotationsAttribute parameterAnnotationsAttr =
                new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.invisibleTag);
        // 1、创建方法参数
        // 1.1 方法参数1
        Annotation annotation1 = new Annotation("org.springframework.web.bind.annotation.PathVariable", constPool);
        annotation1.addMemberValue("value", new StringMemberValue("taskKey", constPool));
        // 1.2 方法参数2
        Annotation annotation2 = new Annotation("org.springframework.web.bind.annotation.RequestParam", constPool);
        annotation2.addMemberValue("value", new StringMemberValue("params", constPool));
        annotation2.addMemberValue("required", new BooleanMemberValue(false, constPool));
        // 1.3 方法参数3
        Annotation annotation3 = new Annotation("org.springframework.web.bind.annotation.RequestBody", constPool);
        annotation3.addMemberValue("required", new BooleanMemberValue(false, constPool));
        // 2、加入方法
        Annotation[][] annotations = new Annotation[3][1];
        annotations[0][0] = annotation1;
        annotations[1][0] = annotation2;
        annotations[2][0] = annotation3;
        parameterAnnotationsAttr.setAnnotations(annotations);
        method.getMethodInfo().addAttribute(parameterAnnotationsAttr);
    }

}
