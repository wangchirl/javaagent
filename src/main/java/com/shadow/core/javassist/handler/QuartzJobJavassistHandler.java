package com.shadow.core.javassist.handler;

import com.shadow.utils.CommonConstants;
import java.util.function.Supplier;

public class QuartzJobJavassistHandler extends AbstractJavassistHandler {

    @Override
    public Supplier<String> getMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append(setThreadLocal());
            body.append("\n    org.quartz.impl.triggers.CronTriggerImpl trigger = (org.quartz.impl.triggers.CronTriggerImpl) ");
            body.append(getArgs().get(CommonConstants.IOC_FIELD_NAME));
            body.append(".getBean($1);");
            body.append("\n    org.quartz.JobKey jobKey = new org.quartz.JobKey(trigger.getJobName());");
            body.append("\n    org.quartz.Scheduler scheduler = ((org.springframework.scheduling.quartz.SchedulerFactoryBean) ");
            body.append(getArgs().get(CommonConstants.IOC_FIELD_NAME));
            body.append(".getBean(org.springframework.scheduling.quartz.SchedulerFactoryBean.class)).getScheduler();");
            if (getThreadLocalClassName() != null && getThreadLocalFieldName() != null) {
                body.append("\n    scheduler.triggerJob(jobKey, jobDataMap);");
            } else {
                body.append("\n    scheduler.triggerJob(jobKey);");
            }
            body.append("\n    return \"Successful execute task job : task key = \" + $1 + \" params = \" + $2 + \" body = \" + $3;");
            body.append("\n}");
            return body.toString();
        };
    }

    @Override
    String setThreadLocal() {
        if (getThreadLocalClassName() != null && getThreadLocalFieldName() != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("org.quartz.JobDataMap jobDataMap = new org.quartz.JobDataMap();");
            builder.append("jobDataMap.put(\"params\", $2);");
            builder.append("jobDataMap.put(\"body\", $3);");
            return builder.toString();
        }
        return "";
    }

    @Override
    protected Supplier<String> getCrudMethodBody() {
        return () -> {
            StringBuilder body = new StringBuilder();
            body.append("{");
            body.append("      boolean result = false;");
            body.append("      java.util.Map message = new java.util.HashMap();");
            body.append("      java.lang.String[] split = $2.split(\"@\");");
            body.append("      if (split.length == 2) {");
            body.append("         java.lang.String triggerName = split[0];");
            body.append("         java.lang.String taskKey = split[1];");
            body.append("         org.quartz.impl.triggers.CronTriggerImpl cronTrigger = (org.quartz.impl.triggers.CronTriggerImpl) ");
            body.append(getArgs().get(CommonConstants.IOC_FIELD_NAME));
            body.append(".getBean(triggerName);");
            body.append("         org.springframework.scheduling.quartz.SchedulerFactoryBean schedulerFactoryBean = (org.springframework.scheduling.quartz.SchedulerFactoryBean) ");
            body.append(getArgs().get(CommonConstants.IOC_FIELD_NAME));
            body.append(".getBean(org.springframework.scheduling.quartz.SchedulerFactoryBean.class);");
            body.append("\n       org.quartz.Scheduler scheduler = schedulerFactoryBean.getScheduler();");
            body.append("\n       if (taskKey.equals(cronTrigger.getJobName())) {");
            body.append("\n         org.quartz.JobKey jobKey = new org.quartz.JobKey(cronTrigger.getJobName());");
            body.append("\n         switch ($1) {");
            body.append("\n           case 0:");
            body.append("\n             scheduler.pauseJob(jobKey);");
            body.append("\n             result = true;");
            body.append("\n             break;");
            body.append("\n           case 1:");
            body.append("\n             cronTrigger.setCronExpression($3);");
            body.append("\n             scheduler.rescheduleJob(cronTrigger.getKey(), cronTrigger);");
            body.append("\n             result = true;");
            body.append("\n             break;");
            body.append("\n           case 2:");
            body.append("\n             scheduler.resumeJob(jobKey);");
            body.append("\n             result = true;");
            body.append("\n             break;");
            body.append("\n           default:");
            body.append("\n             java.util.Set triggerKeys = scheduler.getTriggerKeys(org.quartz.impl.matchers.GroupMatcher.anyGroup());");
            body.append("\n             java.util.Iterator iterator = triggerKeys.iterator();");
            body.append("\n             while (iterator.hasNext()) {");
            body.append("\n               org.quartz.TriggerKey triggerKey = (org.quartz.TriggerKey) iterator.next();");
            body.append("\n               org.quartz.Trigger trigger = scheduler.getTrigger(triggerKey);");
            body.append("\n               org.quartz.TriggerBuilder triggerBuilder = trigger.getTriggerBuilder();");
            body.append("\n               java.lang.reflect.Field field = org.quartz.TriggerBuilder.class.getDeclaredField(\"scheduleBuilder\");");
            body.append("\n               field.setAccessible(true);");
            body.append("\n               org.quartz.CronScheduleBuilder scheduleBuilder = (org.quartz.CronScheduleBuilder) field.get(triggerBuilder);");
            body.append("\n               field = org.quartz.CronScheduleBuilder.class.getDeclaredField(\"cronExpression\");");
            body.append("\n               field.setAccessible(true);");
            body.append("\n               org.quartz.CronExpression cronExpression = (org.quartz.CronExpression) field.get(scheduleBuilder);");
            body.append("\n               $3 = cronExpression.getCronExpression();");
            body.append("\n               message.put(trigger.getJobKey().getName(), $3 + \" 是否停止：\" + scheduler.getTriggerState(triggerKey));");
            body.append("\n             }");
            body.append("\n             result = false;");
            body.append("\n             break;");
            body.append("\n           }");
            body.append("\n         }");
            body.append("\n      }");
            body.append("\n    if (result) {");
            body.append("\n      return \"Success!\";");
            body.append("\n    } else {");
            body.append("\n      return message;");
            body.append("\n    }");
            body.append("\n}");
            return body.toString();
        };
    }
}
