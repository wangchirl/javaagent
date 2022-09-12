package com.shadow.agent;

import com.shadow.core.asm.loadtime.AsmTransformer;
import com.shadow.core.javassist.loadtime.JavassistTransformer;
import com.shadow.utils.CommonUtils;
import com.shadow.utils.CommonConstants;
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
        // 2、必要参数有时才处理
        if (resolveArgs.get(CommonConstants.JOB_TYPE) != null && resolveArgs.get(CommonConstants.CONTROLLER_CLASS) != null) {
            CommonConstants.ScheduleTypeEnum scheduleTypeEnum = CommonConstants.getByJobTypeName(resolveArgs.get(CommonConstants.JOB_TYPE));
            if (scheduleTypeEnum != null) {
                // set default args
                handleCommonDefaultArgs(resolveArgs);
                // transformer
                ClassFileTransformer transformer;
                switch (CommonConstants.getByProxyTypeName(resolveArgs.get(CommonConstants.PROXY_TYPE))) {
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
