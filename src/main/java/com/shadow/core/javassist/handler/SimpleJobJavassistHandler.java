package com.shadow.core.javassist.handler;

import com.shadow.utils.CommonConstants;
import com.shadow.utils.SpringConstants;
import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.annotation.*;

import java.util.List;
import java.util.function.Supplier;

public class SimpleJobJavassistHandler extends AbstractJavassistHandler {

    @Override
    public Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("\n  Object res = null;");
            body.append(setThreadLocal());
            body.append("\n    java.lang.String beanName = com.shadow.supports.helper.ScheduleTaskInfoEnum.getScheduleTaskBeanNameByTaskKey($1);");
            body.append("\n    com.shadow.supports.framework.ICronTriggerTask triggerTask = ");
            body.append(getArgs().getIocFieldName());
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
        if (getArgs().getCrud()) {
            // 1、创建属性
            CtField ctField = CtField.make(CommonConstants.SIMPLE_JOB_IOC_FIELD + getArgs().getCrudFieldName() + CommonConstants.SEMICOLON, cc);
            // 2、设置属性访问权限
            ctField.setModifiers(Modifier.PRIVATE);
            cc.addField(ctField);
            // 3、给属性添加注解
            ctField = cc.getDeclaredField(getArgs().getCrudFieldName());
            List<AttributeInfo> attributes = ctField.getFieldInfo().getAttributes();
            AnnotationsAttribute annotationsAttr = !attributes.isEmpty() ? (AnnotationsAttribute) attributes.get(0) :
                    new AnnotationsAttribute(ctField.getFieldInfo().getConstPool(), AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation(SpringConstants.SPRING_AUTOWIRED_TYPE.getClassName(), ctField.getFieldInfo().getConstPool());
            annotationsAttr.addAnnotation(annotation);
            ctField.getFieldInfo().addAttribute(annotationsAttr);
        }
    }

    @Override
    public Supplier<String> getCrudMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("  switch ($1) {");
            body.append("     case 0:");
            body.append("\n    return ");
            body.append(getArgs().getCrudFieldName());
            body.append(".cancel($2);");
            body.append("     case 1:");
            body.append("\n    return ");
            body.append(getArgs().getCrudFieldName());
            body.append(".update($2, $3);");
            body.append("     case 2:");
            body.append("       java.lang.String var5 = com.shadow.supports.helper.ScheduleTaskInfoEnum.getScheduleTaskBeanNameByTaskKey($2);");
            body.append("       Object var6 = ");
            body.append(getArgs().getIocFieldName());
            body.append(".getBean(var5, com.shadow.supports.framework.ICronTriggerTask.class);");
            body.append("       ((com.shadow.supports.framework.ICronTriggerTask) var6).setTrigger(");
            body.append(getArgs().getIocFieldName());
            body.append(".getEnvironment().getProperty(com.shadow.supports.helper.ScheduleTaskInfoEnum.getScheduleTaskCronNameByTaskKey($2)));");
            body.append("       if ($3 != null) {");
            body.append("         ((com.shadow.supports.framework.ICronTriggerTask) var6).setTrigger($3);");
            body.append("       }");
            body.append("       return ");
            body.append(getArgs().getCrudFieldName());
            body.append(".add((com.shadow.supports.framework.ICronTriggerTask) var6);");
            body.append("     default:");
            body.append("       return ");
            body.append(getArgs().getCrudFieldName());
            body.append(".get(\"\");");
            body.append("  }");
            body.append("\n}");
            return body.toString();
        };
    }
}
