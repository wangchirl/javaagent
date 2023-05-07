package com.shadow.core.javassist.handler;

import java.util.function.Supplier;

public class SpringJobJavassistHandler extends AbstractJavassistHandler {

    @Override
    public Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append(setThreadLocal());
            body.append("\n    org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor postProcessor = ");
            body.append(getArgs().getIocFieldName());
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

    @Override
    public Supplier<String> getCrudMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("   boolean result = false;");
            body.append("\n    org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor processor = (org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor) ");
            body.append(getArgs().getIocFieldName());
            body.append(".getBean(org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor.class);");
            body.append("\n    java.lang.reflect.Field scheduledTasks = org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor.class.getDeclaredField(\"scheduledTasks\");");
            body.append("\n    scheduledTasks.setAccessible(true);");
            body.append("\n    java.util.Map taskMap = (java.util.Map) scheduledTasks.get(processor);");
            body.append("\n    Object key = null;");
            body.append("\n    java.util.Set set = new java.util.HashSet();");
            body.append("\n    java.lang.reflect.Field registrar = org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor.class.getDeclaredField(\"registrar\");");
            body.append("\n    registrar.setAccessible(true);");
            body.append("\n    org.springframework.scheduling.config.ScheduledTaskRegistrar scheduledTaskRegistrar = (org.springframework.scheduling.config.ScheduledTaskRegistrar) registrar.get(processor);");
            body.append("\n    java.util.Map message = new java.util.HashMap();");
            body.append("\n    java.util.Iterator iterator = taskMap.entrySet().iterator();");
            body.append("\n    while (iterator.hasNext()) {");
            body.append("\n      java.util.Map.Entry next = (java.util.Map.Entry) iterator.next();");
            body.append("\n      java.util.Set value = (java.util.Set) next.getValue();");
            body.append("\n      java.util.Iterator iterator1 = value.iterator();");
            body.append("\n      while (iterator1.hasNext()) {");
            body.append("\n        org.springframework.scheduling.config.ScheduledTask scheduledTask = (org.springframework.scheduling.config.ScheduledTask) iterator1.next();");
            body.append("\n        org.springframework.scheduling.config.CronTask task = (org.springframework.scheduling.config.CronTask) scheduledTask.getTask();");
            body.append("\n        org.springframework.scheduling.support.ScheduledMethodRunnable runnable = (org.springframework.scheduling.support.ScheduledMethodRunnable) task.getRunnable();");
            body.append("\n        java.lang.reflect.Field field = org.springframework.scheduling.config.ScheduledTask.class.getDeclaredField(\"future\");");
            body.append("\n        field.setAccessible(true);");
            body.append("\n        java.util.concurrent.ScheduledFuture scheduledFuture = (java.util.concurrent.ScheduledFuture) field.get(scheduledTask);");
            body.append("\n        boolean cancel = scheduledFuture.isCancelled();");
            body.append("\n        message.put(runnable.getMethod().getName(), task.getExpression() + \" 是否停止：\" + cancel);");
            body.append("\n        if (runnable.getMethod().getName().equals($2)) {");
            body.append("\n           key = next.getKey();");
            body.append("\n           set.addAll((java.util.Set) next.getValue());");
            body.append("\n           switch ($1) {");
            body.append("\n             case 0: ");
            body.append("\n               scheduledTask.cancel();");
            body.append("\n               result = true;");
            body.append("\n               break;");
            body.append("\n             case 1:");
            body.append("\n               scheduledTask.cancel();");
            body.append("\n               set.remove(scheduledTask);");
            body.append("\n               scheduledTask = scheduledTaskRegistrar.scheduleCronTask(new org.springframework.scheduling.config.CronTask(task.getRunnable(), $3));");
            body.append("\n               set.add(scheduledTask);");
            body.append("\n               result = true;");
            body.append("\n               break;");
            body.append("\n             case 2: ");
            body.append("\n               scheduledTask.cancel();");
            body.append("\n               set.remove(scheduledTask);");
            body.append("\n               scheduledTask = scheduledTaskRegistrar.scheduleCronTask(new org.springframework.scheduling.config.CronTask(task.getRunnable(), task.getExpression()));");
            body.append("\n               set.add(scheduledTask);");
            body.append("\n               result = true;");
            body.append("\n               break;");
            body.append("\n             default:");
            body.append("\n               result = false;");
            body.append("\n               break;");
            body.append("\n           }");
            body.append("\n        }");
            body.append("\n      }");
            body.append("\n   }");
            body.append("\n   if (key != null && taskMap.get(key) != null) {");
            body.append("\n     taskMap.remove(key);");
            body.append("\n     taskMap.putIfAbsent(key, set);");
            body.append("\n   }");
            body.append("\n   scheduledTasks.set(processor, taskMap);");
            body.append("\n   if (result) {");
            body.append("\n     return \"Success!\";");
            body.append("\n    } else {");
            body.append("\n      return message;");
            body.append("\n    }");
            body.append("\n}");
            return body.toString();
        };
    }
}
