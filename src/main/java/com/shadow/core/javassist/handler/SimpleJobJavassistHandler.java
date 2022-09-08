package com.shadow.core.javassist.handler;

import com.shadow.utils.Constants;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SimpleJobJavassistHandler extends AbstractJavassistHandler {

    public SimpleJobJavassistHandler(Map<String, String> args) {
        super(args);
        if (isDebug()) {
            System.out.println("Javassist Simple Job agent ...");
        }
    }

    @Override
    public Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("\n  Object res = null;");
            body.append(setThreadLocal());
            body.append("\n    java.lang.String beanName = com.shadow.supports.helper.ScheduleTaskInfoEnum.getScheduleTaskBeanNameByTaskKey($1);");
            body.append("\n    com.shadow.supports.framework.ICronTriggerTask triggerTask = ");
            body.append(getArgs().get(Constants.IOC_FIELD_NAME));
            body.append(".getBean(beanName, com.shadow.supports.framework.ICronTriggerTask.class);");
            body.append("\n    triggerTask.run();");
            body.append("\n    res = triggerTask.getResult();");
            body.append(removeThreadLocal());
            body.append("\n    return res;");
            body.append("\n}");
            return body.toString();
        };
    }

    @Override
    public void addFields(CtClass cc) throws Exception {
        if (Boolean.parseBoolean(getArgs().get(Constants.TASK_CRUD))) {
            // 1、创建属性
            CtField ctField = CtField.make(Constants.SIMPLE_JOB_IOC_FIELD + getArgs().get(Constants.SIMPLE_JOB_IOC_FIELD_NAME) + Constants.SEMICOLON, cc);
            // 2、设置属性访问权限
            ctField.setModifiers(Modifier.PRIVATE);
            cc.addField(ctField);
            // 3、给属性添加注解
            ctField = cc.getDeclaredField(getArgs().get(Constants.SIMPLE_JOB_IOC_FIELD_NAME));
            List<AttributeInfo> attributes = ctField.getFieldInfo().getAttributes();
            AnnotationsAttribute annotationsAttr = !attributes.isEmpty() ? (AnnotationsAttribute) attributes.get(0) :
                    new AnnotationsAttribute(ctField.getFieldInfo().getConstPool(), AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation(Constants.SPRING_AUTOWIRED, ctField.getFieldInfo().getConstPool());
            annotationsAttr.addAnnotation(annotation);
            ctField.getFieldInfo().addAttribute(annotationsAttr);
        }
    }

    @Override
    public void addMethods(ClassPool cp, CtClass cc) throws Exception {
        if (Boolean.parseBoolean(getArgs().get(Constants.TASK_CRUD))) {
            CtClass string = cp.get(String.class.getName());
            CtClass object = cp.get(Object.class.getName());
            // 1、创建方法
            CtMethod method = new CtMethod(object, "$$$simpleJobCrud$$$", new CtClass[]{string, string, string}, cc);
            // 2、方法访问权限
            method.setModifiers(Modifier.PUBLIC);
            method.setExceptionTypes(new CtClass[]{cp.get(Exception.class.getName())});
            // 3、设置方法体
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            builder.append("\n       java.lang.Object res;");
            builder.append("\n       if (\"update\".equalsIgnoreCase($1)) {");
            builder.append("\n          res = ");
            builder.append(getArgs().get(Constants.SIMPLE_JOB_IOC_FIELD_NAME));
            builder.append(".update($2, $3);");
            builder.append("\n        } else if (\"cancel\".equalsIgnoreCase($1) || \"delete\".equalsIgnoreCase($1) || \"remove\".equalsIgnoreCase($1)) {");
            builder.append("\n          res = ");
            builder.append(getArgs().get(Constants.SIMPLE_JOB_IOC_FIELD_NAME));
            builder.append(".cancel($2);");
            builder.append("\n        } else if (\"restart\".equalsIgnoreCase($1) || \"reload\".equalsIgnoreCase($1)) {");
            builder.append("\n          res = ");
            builder.append(getArgs().get(Constants.SIMPLE_JOB_IOC_FIELD_NAME));
            builder.append(".restart($2);");
            builder.append("\n        } else if(\"add\".equalsIgnoreCase($1) || \"insert\".equalsIgnoreCase($1)) {");
            builder.append("\n          java.lang.String beanName = com.shadow.supports.helper.ScheduleTaskInfoEnum.getScheduleTaskBeanNameByTaskKey($2);");
            builder.append("\n          com.shadow.supports.framework.ICronTriggerTask triggerTask = ");
            builder.append(getArgs().get(Constants.IOC_FIELD_NAME));
            builder.append(".getBean(beanName, com.shadow.supports.framework.ICronTriggerTask.class);");
            builder.append("\n          triggerTask.setTrigger(");
            builder.append(getArgs().get(Constants.IOC_FIELD_NAME));
            builder.append(".getEnvironment().getProperty(com.shadow.supports.helper.ScheduleTaskInfoEnum.getScheduleTaskCronNameByTaskKey($2)));");
            builder.append("\n          if($3 != null && $3.length() > 6) {");
            builder.append("\n              triggerTask.setTrigger($3);");
            builder.append("\n          };");
            builder.append("\n          res = ");
            builder.append(getArgs().get(Constants.SIMPLE_JOB_IOC_FIELD_NAME));
            builder.append(".add(triggerTask);");
            builder.append("\n        } else {");
            builder.append("\n          res = ");
            builder.append(getArgs().get(Constants.SIMPLE_JOB_IOC_FIELD_NAME));
            builder.append(".get($2);");
            builder.append("\n        }");
            builder.append("\n      return res;");
            builder.append("\n}");
            method.setBody(builder.toString());
            cc.addMethod(method);

            ClassFile ccFile = cc.getClassFile();
            ConstPool constPool = ccFile.getConstPool();
            AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            // 1、创建方法注解
            Annotation annotation = new Annotation(Constants.SPRING_REQUEST_MAPPING, constPool);
            // 1.1 设置注解参数
            StringMemberValue path1 = new StringMemberValue("/{opt}/{taskKey}", constPool);
            ArrayMemberValue arrayMemberValue = new ArrayMemberValue(constPool);
            arrayMemberValue.setValue(new MemberValue[]{path1});
            annotation.addMemberValue(Constants.SPRING_REQUEST_MAPPING_PATH, arrayMemberValue);
            // 2、添加到方法上
            methodAttr.addAnnotation(annotation);
            method.getMethodInfo().addAttribute(methodAttr);

            ParameterAnnotationsAttribute parameterAnnotationsAttr =
                    new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
            // 1、创建方法参数
            // 1.1 方法参数1
            Annotation annotation1 = new Annotation(Constants.SPRING_PATH_VARIABLE, constPool);
            annotation1.addMemberValue(Constants.SPRING_REQUEST_MAPPING_VALUE, new StringMemberValue(Constants.SPRING_PATH_VARIABLE_PARAMETER_NAME_OPT, constPool));
            // 1.2 方法参数2
            Annotation annotation2 = new Annotation(Constants.SPRING_PATH_VARIABLE, constPool);
            annotation2.addMemberValue(Constants.SPRING_REQUEST_MAPPING_VALUE, new StringMemberValue(Constants.SPRING_PATH_VARIABLE_PARAMETER_NAME_TASK_KEY, constPool));
            // 1.3 方法参数3
            Annotation annotation3 = new Annotation(Constants.SPRING_REQUEST_PARAM, constPool);
            annotation3.addMemberValue(Constants.SPRING_REQUEST_MAPPING_VALUE, new StringMemberValue(Constants.SPRING_REQUEST_PARAM_NAME, constPool));
            annotation3.addMemberValue(Constants.SPRING_REQUEST_PARAM_REQUIRED, new BooleanMemberValue(false, constPool));

            // 2、加入方法
            Annotation[][] annotations = new Annotation[3][1];
            annotations[0][0] = annotation1;
            annotations[1][0] = annotation2;
            annotations[2][0] = annotation3;
            parameterAnnotationsAttr.setAnnotations(annotations);
            method.getMethodInfo().addAttribute(parameterAnnotationsAttr);
        }
    }
}
