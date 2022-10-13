package com.shadow.utils;

import jdk.internal.org.objectweb.asm.Type;

public class BaseConstants {

    private BaseConstants() {

    }

    // 🌹🌹🌹🌹🌹🌹🌹🌹 基础类及其描述符 🌹🌹🌹🌹🌹🌹🌹🌹

    public static Type STRING_TYPE = Type.getType("Ljava/lang/String;");
    public static Type OBJECT_TYPE = Type.getType("Ljava/lang/Object;");
    public static Type CLASS_TYPE = Type.getType("Ljava/lang/Class;");
    public static Type EXCEPTION_TYPE = Type.getType("Ljava/lang/Exception;");
    public static Type RUNNABLE_TYPE = Type.getType("Ljava/lang/Runnable;");
    public static Type REFLECT_FIELD_TYPE = Type.getType("Ljava/lang/reflect/Field;");
    public static Type REFLECT_METHOD_TYPE = Type.getType("Ljava/lang/reflect/Method;");
    public static Type THREADLOCAL_TYPE = Type.getType("Ljava/lang/ThreadLocal;");
    public static Type ITERATOR_TYPE = Type.getType("Ljava/util/Iterator;");
    public static Type SET_TYPE = Type.getType("Ljava/util/Set;");
    public static Type MAP_TYPE = Type.getType("Ljava/util/Map;");
    public static Type BOOLEAN_TYPE = Type.getType("Ljava/lang/Boolean;");

    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // (Ljava/lang/String;)[Ljava/lang/String;
    public static String AS_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS +  CommonConstants.LEFT_BIG_BRACKETS + STRING_TYPE.getDescriptor();

    // ()V
    public static String V_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // ()Ljava/lang/String;
    public static String S_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + STRING_TYPE.getDescriptor();

    // ()Ljava/lang/Object;
    public static String O_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // ()I
    public static String I_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.I;

    // (Ljava/lang/String;)Ljava/lang/Boolean;
    public static String BOOLEAN_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + BOOLEAN_TYPE.getDescriptor();

    // (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;
    public static String BOOLEAN_S_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + BOOLEAN_TYPE.getDescriptor();

    // (Ljava/lang/Object;)Ljava/lang/Object;
    public static String O_O = CommonConstants.LEFT_BRACKETS + OBJECT_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    public static String O_O_O = CommonConstants.LEFT_BRACKETS + OBJECT_TYPE.getDescriptor() + OBJECT_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (Ljava/lang/String;)Ljava/lang/String;
    public static String S_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + STRING_TYPE.getDescriptor();

    // (Ljava/lang/String;)V
    public static String V_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/Object;)V
    public static String V_O = CommonConstants.LEFT_BRACKETS + OBJECT_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Z)V
    public static String V_Z = CommonConstants.LEFT_BRACKETS + CommonConstants.JavaTypeEnum.Z + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/String;Ljava/lang/String;)V
    public static String V_SS = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    public static String V_SSO = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + STRING_TYPE.getDescriptor() + OBJECT_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    public static String O_SSO = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + STRING_TYPE.getDescriptor() + OBJECT_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (ILjava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    public static String O_ISS = CommonConstants.LEFT_BRACKETS + CommonConstants.JavaTypeEnum.I + STRING_TYPE.getDescriptor() + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    public static String O_SO = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + OBJECT_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    public static String O_SC_ = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CLASS_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (Ljava/lang/String;)Ljava/lang/Object;
    public static String O_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    public static String O_S_C_ = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CLASS_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // (Ljava/lang/Class;)Ljava/lang/Object;
    public static String O_C_ = CommonConstants.LEFT_BRACKETS + CLASS_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + OBJECT_TYPE.getDescriptor();

    // ()Z
    public static String Z_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.Z;

    // (Ljava/lang/Object;)Z
    public static String Z_O = CommonConstants.LEFT_BRACKETS + OBJECT_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.Z;

    // ()Ljava/util/Set;
    public static String SET_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SET_TYPE.getDescriptor();

    // ()Ljava/util/Iterator;
    public static String ITERATOR_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + ITERATOR_TYPE.getDescriptor();

    // ()Ljava/lang/Runnable;
    public static String RUNNABLE_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + RUNNABLE_TYPE.getDescriptor();

    // ()Ljava/lang/reflect/Method;
    public static String REFLECT_METHOD_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + REFLECT_METHOD_TYPE.getDescriptor();

    // (Ljava/lang/String;)Ljava/lang/reflect/Field;
    public static String REFLECT_FIELD_S = CommonConstants.LEFT_BRACKETS + STRING_TYPE.getDescriptor() + CommonConstants.RIGHT_BRACKETS + REFLECT_FIELD_TYPE.getDescriptor();


}
