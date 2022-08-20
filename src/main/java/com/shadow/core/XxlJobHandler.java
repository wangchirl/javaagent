package com.shadow.core;

import java.util.function.Supplier;

class XxlJobHandler extends AbstractHandler {

    XxlJobHandler() {
        System.out.println("Xxl Job Agent ...");
    }

    @Override
    Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("\n    com.xxl.job.core.executor.XxlJobExecutor xxlJobExecutor = agentApplicationContext.getBean(com.xxl.job.core.executor.XxlJobExecutor.class);");
            body.append("\n    java.lang.reflect.Field field = com.xxl.job.core.executor.XxlJobExecutor.class.getDeclaredField(\"jobHandlerRepository\");");
            body.append("\n    field.setAccessible(true);");
            body.append("\n    java.lang.Object jobHandlerRepository = field.get(xxlJobExecutor);");
            body.append("\n    com.xxl.job.core.handler.IJobHandler handler = (com.xxl.job.core.handler.IJobHandler)((java.util.Map)jobHandlerRepository).get($1);");
            body.append("\n    handler.execute();");
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }
}
