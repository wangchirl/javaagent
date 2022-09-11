package com.shadow.utils;

public class BaseConstants {

    private BaseConstants() {

    }

    // 类描述符前后缀
    public static String CLASS_DESCRIPTOR_PREFIX = "L";
    public static String CLASS_DESCRIPTOR_SUFFIX = ";";

    // 🌹🌹🌹🌹🌹🌹🌹🌹 基础类及其描述符 🌹🌹🌹🌹🌹🌹🌹🌹
    /**
     * class name
     */
    public static String STRING_CLASS = "java.lang.String";
    public static String OBJECT_CLASS = "java.lang.Object";
    public static String CLASS_CLASS = "java.lang.Class";
    public static String EXCEPTION_CLASS = "java.lang.Exception";
    public static String RUNNABLE_CLASS = "java.lang.Runnable";
    public static String REFLECT_FIELD_CLASS = "java.lang.reflect.Field";
    public static String REFLECT_METHOD_CLASS = "java.lang.reflect.Method";
    public static String THREADLOCAL_CLASS = "java.lang.ThreadLocal";
    public static String ITERATOR_CLASS = "java.util.Iterator";
    public static String SET_CLASS = "java.util.Set";
    public static String MAP_CLASS = "java.util.Map";
    public static String BOOLEAN_CLASS = "java.lang.Boolean";

    /**
     * internal class name
     */
    public static String STRING_INTERNAL_CLASS = STRING_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String OBJECT_INTERNAL_CLASS = OBJECT_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String CLASS_INTERNAL_CLASS = CLASS_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String EXCEPTION_INTERNAL_CLASS = EXCEPTION_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String RUNNABLE_INTERNAL_CLASS = RUNNABLE_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String REFLECT_FIELD_INTERNAL_CLASS = REFLECT_FIELD_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String REFLECT_METHOD_INTERNAL_CLASS = REFLECT_METHOD_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String THREADLOCAL_INTERNAL_CLASS = THREADLOCAL_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String ITERATOR_INTERNAL_CLASS = ITERATOR_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String SET_INTERNAL_CLASS = SET_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String MAP_INTERNAL_CLASS = MAP_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);
    public static String BOOLEAN_INTERNAL_CLASS = BOOLEAN_CLASS.replaceAll(CommonConstants.DOT, CommonConstants.BIAS);

    // Ljava/lang/String;
    public static String STRING_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + STRING_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/Object;
    public static String OBJECT_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + OBJECT_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/Class;
    public static String CLASS_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + CLASS_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/Exception;
    public static String EXCEPTION_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + EXCEPTION_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/Runnable;
    public static String RUNNABLE_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + RUNNABLE_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/reflect/Field;
    public static String REFLECT_FIELD_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + REFLECT_FIELD_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/reflect/Method;
    public static String REFLECT_METHOD_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + REFLECT_METHOD_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/ThreadLocal;
    public static String THREADLOCAL_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + THREADLOCAL_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/util/Iterator;
    public static String ITERATOR_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + ITERATOR_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/util/Set;
    public static String SET_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + SET_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/util/Map;
    public static String MAP_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + MAP_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;

    // Ljava/lang/Boolean;
    public static String BOOLEAN_DESCRIPTOR = CLASS_DESCRIPTOR_PREFIX + BOOLEAN_INTERNAL_CLASS + CLASS_DESCRIPTOR_SUFFIX;


    // ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀ 常见的方法描述符 ❀ ❀ ❀ ❀ ❀ ❀ ❀ ❀

    // ()V
    public static String V_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // ()Ljava/lang/String;
    public static String S_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + STRING_DESCRIPTOR;

    // ()Ljava/lang/Object;
    public static String O_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // ()I
    public static String I_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.I;

    // (Ljava/lang/String;)Ljava/lang/Boolean;
    public static String BOOLEAN_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + BOOLEAN_DESCRIPTOR;

    // (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;
    public static String BOOLEAN_S_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + BOOLEAN_DESCRIPTOR;

    // (Ljava/lang/Object;)Ljava/lang/Object;
    public static String O_O = CommonConstants.LEFT_BRACKETS + OBJECT_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // (Ljava/lang/String;)Ljava/lang/String;
    public static String S_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + STRING_DESCRIPTOR;

    // (Ljava/lang/String;)V
    public static String V_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/Object;)V
    public static String V_O = CommonConstants.LEFT_BRACKETS + OBJECT_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Z)V
    public static String V_Z = CommonConstants.LEFT_BRACKETS + CommonConstants.JavaTypeEnum.Z + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/String;Ljava/lang/String;)V
    public static String V_SS = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    public static String V_SSO = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + STRING_DESCRIPTOR + OBJECT_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.V;

    // (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    public static String O_SSO = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + STRING_DESCRIPTOR + OBJECT_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // (ILjava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    public static String O_ISS = CommonConstants.LEFT_BRACKETS + CommonConstants.JavaTypeEnum.I + STRING_DESCRIPTOR + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    public static String O_SO = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + OBJECT_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    public static String O_SC_ = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CLASS_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // (Ljava/lang/String;)Ljava/lang/Object;
    public static String O_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    public static String O_S_C_ = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CLASS_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // (Ljava/lang/Class;)Ljava/lang/Object;
    public static String O_C_ = CommonConstants.LEFT_BRACKETS + CLASS_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + OBJECT_DESCRIPTOR;

    // ()Z
    public static String Z_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.Z;

    // (Ljava/lang/Object;)Z
    public static String Z_O = CommonConstants.LEFT_BRACKETS + OBJECT_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + CommonConstants.JavaTypeEnum.Z;

    // ()Ljava/util/Set;
    public static String SET_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + SET_DESCRIPTOR;

    // ()Ljava/util/Iterator;
    public static String ITERATOR_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + ITERATOR_DESCRIPTOR;

    // ()Ljava/lang/Runnable;
    public static String RUNNABLE_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + RUNNABLE_DESCRIPTOR;

    // ()Ljava/lang/reflect/Method;
    public static String REFLECT_METHOD_ = CommonConstants.LEFT_BRACKETS + CommonConstants.RIGHT_BRACKETS + REFLECT_METHOD_DESCRIPTOR;

    // (Ljava/lang/String;)Ljava/lang/reflect/Field;
    public static String REFLECT_FIELD_S = CommonConstants.LEFT_BRACKETS + STRING_DESCRIPTOR + CommonConstants.RIGHT_BRACKETS + REFLECT_FIELD_DESCRIPTOR;


}
