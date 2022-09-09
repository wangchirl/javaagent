package com.shadow.core.javassist.handler;

import javassist.ClassPool;
import javassist.CtClass;

public interface IJavassistHandler {

    /**
     * 添加额外的字段
     * 目前只有 Simple Job 添加了额外的字段
     */
    default void addFields(CtClass cc) throws Exception {

    }

    /**
     * 添加额外的方法，默认空实现
     * 目前只有 Simple Job 添加了额外的增删改方法
     */
    default void addMethods(ClassPool cp, CtClass cc) throws Exception {

    }

}
