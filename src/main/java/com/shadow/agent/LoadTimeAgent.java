package com.shadow.agent;

import com.shadow.common.RequestArgsVO;
import com.shadow.core.asm.loadtime.AsmTransformer;
import com.shadow.core.buddy.loadtime.BuddyTransformer;
import com.shadow.core.javassist.loadtime.JavassistTransformer;
import com.shadow.utils.CommonUtils;
import com.shadow.utils.CommonConstants;
import com.shadow.utils.ParamResolveUtils;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;


public class LoadTimeAgent extends BaseAgent {

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        // 1、解析参数
        RequestArgsVO argsBean = ParamResolveUtils.resolveArgs(agentArgs);
        // if log allowed
        CommonUtils.printLogAllowed(argsBean);
        // 2、必要参数有时才处理
        if (argsBean.getJobType() != null && argsBean.getCtlClass() != null) {
            CommonConstants.JobTypeEnum jobTypeEnum = CommonConstants.getByJobTypeName(argsBean.getJobType());
            if (jobTypeEnum != null) {
                // set default args
                handleCommonDefaultArgs(argsBean);
                // transformer
                ClassFileTransformer transformer = null;
                CommonConstants.ProxyTypeEnum proxyTypeEnum = CommonConstants.getByProxyTypeName(argsBean.getProxyType());
                System.out.println("JOB   TYPE: " + jobTypeEnum);
                System.out.println("PROXY TYPE: " + proxyTypeEnum);
                switch (proxyTypeEnum) {
                    case ASM:
                        transformer = new AsmTransformer();
                        break;
                    case BUDDY:
                        new BuddyTransformer().handle(inst);
                        break;
                    default:
                        transformer = new JavassistTransformer();
                        break;
                }
                // add redefined transformer
                if (transformer != null) inst.addTransformer(transformer);
            }
        }
    }
}
