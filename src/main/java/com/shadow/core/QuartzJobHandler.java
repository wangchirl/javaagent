package com.shadow.core;

import com.shadow.utils.Constants;

import java.util.Map;
import java.util.function.Supplier;

class QuartzJobHandler extends AbstractHandler {

    QuartzJobHandler(Map<String, String> args) {
        super(args);
        System.out.println("Quartz Job agent ...");
    }

    @Override
    Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append(setThreadLocal());
            body.append("\n    org.quartz.impl.triggers.CronTriggerImpl trigger = (org.quartz.impl.triggers.CronTriggerImpl) ");
            body.append(getArgs().get(Constants.IOC_FIELD_NAME));
            body.append(".getBean($1);");
            body.append("\n    org.quartz.JobKey jobKey = new org.quartz.JobKey(trigger.getJobName());");
            body.append("\n    org.quartz.Scheduler scheduler = ((org.springframework.scheduling.quartz.SchedulerFactoryBean) ");
            body.append(getArgs().get(Constants.IOC_FIELD_NAME));
            body.append(".getBean(org.springframework.scheduling.quartz.SchedulerFactoryBean.class)).getScheduler();");
            body.append("\n    scheduler.triggerJob(jobKey, jobDataMap);");
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }

    @Override
    String setThreadLocal() {
        StringBuilder builder = new StringBuilder();
        builder.append("org.quartz.JobDataMap jobDataMap = new org.quartz.JobDataMap();");
        builder.append("jobDataMap.put(\"params\", $2);");
        builder.append("jobDataMap.put(\"body\", $3);");
        return builder.toString();
    }
}
