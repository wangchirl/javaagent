package com.shadow.agent;

import com.shadow.core.JavassistTransformer;
import com.shadow.utils.Constants;

import java.lang.instrument.Instrumentation;

public class LoadTimeAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Premain-Class: " + LoadTimeAgent.class.getName());
        System.out.println("Instrumentation Class: " + inst.getClass().getName());
        System.out.println(inst.isRedefineClassesSupported());
        System.out.println(inst.isRetransformClassesSupported());
        System.out.println(inst.isNativeMethodPrefixSupported());
        System.out.println("agentArgs: " + agentArgs);
        System.out.println("===========================");

        // 参数解析
        String[] argues = agentArgs.split(Constants.COMM);
        if(argues.length == 2) {
            Constants.ScheduleTypeEnum scheduleTypeEnum = Constants.getByName(argues[0]);
            if(scheduleTypeEnum != null) {
                // transformer
                JavassistTransformer transformer = new JavassistTransformer(argues[1], scheduleTypeEnum);
                // add redefined transformer
                inst.addTransformer(transformer);
            }
        }

    }

}
