package com.shadow.core.buddy.handler;

import net.bytebuddy.jar.asm.ClassVisitor;

public interface IBuddyHandler {

    /**
     * add more fields
     */
    default void addFields(ClassVisitor classVisitor){

    }

    /**
     * add more method
     */
    default void addMethods(ClassVisitor classVisitor) {

    }


}
