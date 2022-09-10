package com.shadow.core.javassist.handler;

import com.shadow.utils.CommonConstants;
import com.shadow.utils.XxlConstants;

import java.util.Map;
import java.util.function.Supplier;

public class XxlJobJavassistHandler extends AbstractJavassistHandler {

    public XxlJobJavassistHandler(Map<String, String> args) {
        super(args);
        if (isDebug()) {
            System.out.println(XxlConstants.JAVASSIST_PROXY_LOG_TIPS);
        }
    }

    @Override
    public Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append(setThreadLocal());
            body.append("\n    com.xxl.job.core.executor.XxlJobExecutor xxlJobExecutor = ");
            body.append(getArgs().get(CommonConstants.IOC_FIELD_NAME));
            body.append(".getBean(com.xxl.job.core.executor.XxlJobExecutor.class);");
            body.append("\n    java.lang.reflect.Field field = com.xxl.job.core.executor.XxlJobExecutor.class.getDeclaredField(\"jobHandlerRepository\");");
            body.append("\n    field.setAccessible(true);");
            body.append("\n    java.lang.Object jobHandlerRepository = field.get(xxlJobExecutor);");
            body.append("\n    com.xxl.job.core.handler.IJobHandler handler = (com.xxl.job.core.handler.IJobHandler)((java.util.Map)jobHandlerRepository).get($1);");
            body.append("\n    handler.execute();");
            body.append(removeThreadLocal());
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }
}
