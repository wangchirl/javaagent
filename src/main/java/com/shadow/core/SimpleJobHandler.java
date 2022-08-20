package com.shadow.core;

import java.util.function.Supplier;

class SimpleJobHandler extends AbstractHandler {

    SimpleJobHandler() {
        System.out.println("Simple Job agent ...");
    }

    /**
     * TODO
     */
    @Override
    Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("\n    scheduler.triggerJob(jobKey);");
            body.append("\n    scheduler.triggerJob(jobKey);");
            body.append("\n    scheduler.triggerJob(jobKey);");
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }
}
