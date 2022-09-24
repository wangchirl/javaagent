package com.shadow.core.buddy.loadtime;

import com.shadow.core.AbstractTransformer;
import com.shadow.core.buddy.handler.AbstractBuddyHandler;
import com.shadow.core.buddy.handler.IBuddyHandler;
import com.shadow.utils.CommonConstants;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Map;
import java.util.ServiceLoader;

public class BuddyTransformer extends AbstractTransformer {

    /**
     * current handle
     */
    private AbstractBuddyHandler handler;

    public BuddyTransformer(Map<String, String> resolveArgs, CommonConstants.ScheduleTypeEnum scheduleTypeEnum) {
        super(resolveArgs);
        // SPI
        ServiceLoader<IBuddyHandler> handlers = ServiceLoader.load(IBuddyHandler.class);
        for (IBuddyHandler handler : handlers) {
            ((AbstractBuddyHandler) handler).setArgs(resolveArgs);
            ((AbstractBuddyHandler) handler).initInnerClassName();
            if (scheduleTypeEnum.name().equalsIgnoreCase(handler.getClass().getSimpleName().replace(CommonConstants.BYTEBUDDY_HANDLER_NAME_SUFFIX, ""))) {
                this.handler = (AbstractBuddyHandler) handler;
                break;
            }
        }
    }

    public ClassFileTransformer handle(Instrumentation inst) {
        return handler.handle(inst);
    }

}
