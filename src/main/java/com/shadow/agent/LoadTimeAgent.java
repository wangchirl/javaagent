package com.shadow.agent;

import com.shadow.core.asm.loadtime.AsmTransformer;
import com.shadow.core.javassist.loadtime.JavassistTransformer;
import com.shadow.utils.CommonUtils;
import com.shadow.utils.Constants;
import com.shadow.utils.ParamResolveUtils;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Map;

public class LoadTimeAgent extends BaseAgent {

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
            Constants.ScheduleTypeEnum scheduleTypeEnum = Constants.getByJobTypeName(resolveArgs.get(Constants.JOB_TYPE));
            if (scheduleTypeEnum != null) {
                // set default args
                handleCommonDefaultArgs(resolveArgs);
                // transformer
                ClassFileTransformer transformer;
                switch (Constants.getByProxyTypeName(resolveArgs.get(Constants.PROXY_TYPE))) {
                    case ASM:
                        transformer = new AsmTransformer(resolveArgs, scheduleTypeEnum);
                        break;
                    case BUDDY:
                        // TODO
                        throw new RuntimeException("暂不支持的操作");
                    default:
                        transformer = new JavassistTransformer(resolveArgs, scheduleTypeEnum);
                        break;
                }
                // add redefined transformer
                inst.addTransformer(transformer);
            }
        }
    }


}
