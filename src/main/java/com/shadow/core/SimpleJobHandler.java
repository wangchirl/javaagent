package com.shadow.core;

import java.util.function.Supplier;

class SimpleJobHandler extends AbstractHandler {

    SimpleJobHandler() {
        System.out.println("Simple Job agent ...");
    }
    
    @Override
    Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("\n    java.lang.String beanName = com.shadow.supports.helper.ScheduleTaskInfoEnum.getScheduleTaskBeanNameByTaskKey($1);");
            body.append("\n    com.shadow.supports.framework.ICronTriggerTask triggerTask = agentApplicationContext.getBean(beanName, com.shadow.supports.framework.ICronTriggerTask.class);");
            body.append("\n    triggerTask.run();");
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }
}
