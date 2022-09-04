package com.shadow.core.javassist.loadtime;

import com.shadow.utils.Constants;

import java.util.Map;
import java.util.function.Supplier;

public class SpringJobJavassistHandler extends AbstractJavassistHandler {

    public SpringJobJavassistHandler(Map<String, String> args) {
        super(args);
        System.out.println("Javassist Spring Job agent ...");
    }

    @Override
    public Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append(setThreadLocal());
            body.append("\n    org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor postProcessor = ");
            body.append(getArgs().get(Constants.IOC_FIELD_NAME));
            body.append(".getBean(org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor.class);");
            body.append("\n    java.lang.reflect.Field field = org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor.class.getDeclaredField(\"scheduledTasks\");");
            body.append("\n    field.setAccessible(true);");
            body.append("\n    Object o1 = field.get(postProcessor);");
            body.append("\n    java.util.Map map = (java.util.Map) o1;");
            body.append("\n    java.util.Collection values = map.values();");
            body.append("\n    java.util.Set result = new java.util.LinkedHashSet();");
            body.append("\n    Object[] array = values.toArray();");
            body.append("\n    for (int i = 0; i < array.length; i++) {;");
            body.append("\n        java.util.Set sets = (java.util.Set)array[i];");
            body.append("\n        Object[] objects = sets.toArray();");
            body.append("\n        for (int j = 0; j < objects.length; j++) {");
            body.append("\n            Object object = objects[j];");
            body.append("\n            result.add(object);");
            body.append("\n        };");
            body.append("\n    };");
            body.append("\n    field = org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor.class.getDeclaredField(\"registrar\");");
            body.append("\n    field.setAccessible(true);");
            body.append("\n    Object o2 = field.get(postProcessor);");
            body.append("\n    org.springframework.scheduling.config.ScheduledTaskRegistrar registrar = (org.springframework.scheduling.config.ScheduledTaskRegistrar) o2;");
            body.append("\n    field = org.springframework.scheduling.config.ScheduledTaskRegistrar.class.getDeclaredField(\"scheduledTasks\");");
            body.append("\n    field.setAccessible(true);");
            body.append("\n    Object o3 = field.get(registrar);");
            body.append("\n    java.util.Set set = (java.util.Set)o3;");
            body.append("\n    result.addAll(set);");
            body.append("\n    Object[] objects = result.toArray();");
            body.append("\n    for (int i = 0; i < objects.length; i++) {;");
            body.append("\n        Object object = objects[i];");
            body.append("\n        org.springframework.scheduling.config.ScheduledTask scheduledTask = (org.springframework.scheduling.config.ScheduledTask)object;");
            body.append("\n        org.springframework.scheduling.config.Task task = scheduledTask.getTask();");
            body.append("\n        org.springframework.scheduling.support.ScheduledMethodRunnable runnable = (org.springframework.scheduling.support.ScheduledMethodRunnable) task.getRunnable();");
            body.append("\n        if(runnable.getMethod().getName().equals($1)) {;");
            body.append("\n            runnable.run();");
            body.append("\n            break;");
            body.append("\n        };");
            body.append("\n    };");
            body.append(removeThreadLocal());
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }
}
