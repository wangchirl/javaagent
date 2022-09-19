package com.shadow.core.buddy.handler;

import net.bytebuddy.jar.asm.ClassVisitor;

public interface IBuddyHandler {

    /**
     * 添加额外的字段
     * 目前只有 Simple Job 添加了额外的字段
     */
    default void addFields(ClassVisitor classVisitor){

    }

    /**
     * 添加额外的方法，默认空实现
     * 目前只有 Simple Job 添加了额外的增删改方法
     */
    default void addMethods(ClassVisitor classVisitor) {

    }


}
