package com.shadow.core.buddy.loadtime;

import com.shadow.core.AbstractHandler;

import java.util.Map;

public abstract class AbstractBuddyHandler extends AbstractHandler implements IBuddyHandler{

    AbstractBuddyHandler(Map<String, String> args) {
        super(args);
    }
}
