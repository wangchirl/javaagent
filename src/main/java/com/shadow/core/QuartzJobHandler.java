package com.shadow.core;

import java.util.function.Supplier;

class QuartzJobHandler extends AbstractHandler {

    QuartzJobHandler() {
        System.out.println("Quartz Job agent ...");
    }

    @Override
    Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("\n    org.quartz.impl.triggers.CronTriggerImpl trigger = (org.quartz.impl.triggers.CronTriggerImpl) agentApplicationContext.getBean($1);");
            body.append("\n    org.quartz.JobKey jobKey = new org.quartz.JobKey(trigger.getJobName());");
            body.append("\n    org.quartz.Scheduler scheduler = ((org.springframework.scheduling.quartz.SchedulerFactoryBean) agentApplicationContext.getBean(org.springframework.scheduling.quartz.SchedulerFactoryBean.class)).getScheduler();");
            body.append("\n    scheduler.triggerJob(jobKey);");
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }
}
