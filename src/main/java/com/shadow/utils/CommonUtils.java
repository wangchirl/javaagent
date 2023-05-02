package com.shadow.utils;

import com.shadow.common.RequestArgsVO;

import java.io.PrintStream;

public class CommonUtils {

    private CommonUtils() {

    }

    public static void printLogAllowed(RequestArgsVO argsBean) {
        // System.out 打印是否开启
        if (!argsBean.getLogger()) {
            System.setOut(new PrintStream(System.out) {
                @Override
                public void println(String x) {
                    // do nothing
                }
            });
        }
    }

}
