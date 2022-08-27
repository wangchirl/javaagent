package com.shadow.core.javassist.loadtime;

import com.shadow.utils.Constants;

import java.util.Map;
import java.util.function.Supplier;

public class SimpleJobJavassistHandler extends AbstractJavassistHandler {

    public SimpleJobJavassistHandler(Map<String, String> args) {
        super(args);
        System.out.println("Simple Job agent ...");
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
}
