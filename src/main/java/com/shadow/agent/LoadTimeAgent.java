package com.shadow.agent;

import com.shadow.core.javassist.loadtime.JavassistTransformer;
import com.shadow.utils.CommonUtils;
import com.shadow.utils.Constants;
import com.shadow.utils.ParamResolveUtils;

import java.lang.instrument.Instrumentation;
import java.util.Map;

public class LoadTimeAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        // 1、解析参数
        Map<String, String> resolveArgs = ParamResolveUtils.resolveArgs(agentArgs);
        // if log allowed
        CommonUtils.printLogAllowed(resolveArgs);

        System.out.println("Premain-Class: " + LoadTimeAgent.class.getName());
        System.out.println("Instrumentation Class: " + inst.getClass().getName());
        System.out.println("RedefineClasses Supported : " + inst.isRedefineClassesSupported());
        System.out.println("RetransformClasses Supported: " + inst.isRetransformClassesSupported());
        System.out.println("NativeMethodPrefix Supported: " + inst.isNativeMethodPrefixSupported());
        System.out.println("agentArgs: " + agentArgs);
        System.out.println("===========================");

        // 2、必要参数有时才处理
        if (resolveArgs.get(Constants.JOB_TYPE) != null && resolveArgs.get(Constants.CONTROLLER_CLASS) != null) {
            Constants.ScheduleTypeEnum scheduleTypeEnum = Constants.getByName(resolveArgs.get(Constants.JOB_TYPE));
            if (scheduleTypeEnum != null) {
                // set default args
                handleDefaultArgs(resolveArgs);
                // transformer
                JavassistTransformer transformer = new JavassistTransformer(resolveArgs, scheduleTypeEnum);
                // add redefined transformer
                inst.addTransformer(transformer);
            }
        }
    }

    private static void handleDefaultArgs(Map<String, String> resolveArgs) {
        // 1、DEBUG
        resolveArgs.putIfAbsent(Constants.DEBUG, "false");
        // 2、CRUD 相关
        resolveArgs.putIfAbsent(Constants.TASK_CRUD, "false");
        resolveArgs.computeIfAbsent(Constants.SIMPLE_JOB_IOC_FIELD_NAME, k -> Constants.DEFAULT_SIMPLE_JOB_IOC_FIELD_NAME);
        // 3、HTTP_REQUEST_URI
        if (resolveArgs.get(Constants.HTTP_REQUEST_PREFIX_URI) != null) {
            resolveArgs.put(Constants.HTTP_REQUEST_PREFIX_URI, resolveArgs.get(Constants.HTTP_REQUEST_PREFIX_URI) + Constants.PATH_SUFFIX);
        } else {
            resolveArgs.put(Constants.HTTP_REQUEST_PREFIX_URI, Constants.DEFAULT_HTTP_PATH_PREFIX + Constants.PATH_SUFFIX);
        }
        // 4、IOC_FIELD_NAME
        resolveArgs.computeIfAbsent(Constants.IOC_FIELD_NAME, k -> Constants.DEFAULT_IOC_FIELD_VALUE);

    }
}
