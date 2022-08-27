package com.shadow.utils;

import java.io.PrintStream;
import java.util.Map;

public class CommonUtils {

    private CommonUtils() {

    }

    public static void printLogAllowed(Map<String, String> resolveArgs) {
        // System.out 打印是否开启
        if (resolveArgs.get(Constants.LOGGER_PRINT_OPEN) == null ||
                !Boolean.parseBoolean(resolveArgs.get(Constants.LOGGER_PRINT_OPEN))) {
            System.setOut(new PrintStream(System.out) {
                @Override
                public void println(String x) {
                    // do nothing
                }
            });
        }
    }

}
