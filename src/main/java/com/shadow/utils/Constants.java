package com.shadow.utils;

public class Constants {

    private Constants() {

    }

    public static String EQUAL = "=";
    public static String COMM = ",";
    public static String DOT = "\\.";
    public static String BIAS = "/";
    public static String AND = "&";
    public static String OR = "|";
    public static String SPACE = " ";
    public static String SEMICOLON = ";";
    public static String DOLLER_S = "$";
    public static String DOLLER_D = "$$";

    public static String DEFAULT_PATH = "/agent/run";

    public enum ScheduleTypeEnum {
        XXL,
        QUARTZ,
        SPRING,
        SIMPLE
    }

    public static ScheduleTypeEnum getByName(String type) {
        for (ScheduleTypeEnum scheduleTypeEnum : ScheduleTypeEnum.values()) {
            if(scheduleTypeEnum.name().equalsIgnoreCase(type)) {
                return scheduleTypeEnum;
            }
        }
        return null;
    }
}
