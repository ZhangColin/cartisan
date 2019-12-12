package com.cartisan.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ThrowableUtil {
    private ThrowableUtil() {
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter printWriter = new PrintWriter(stringWriter)){
            throwable.printStackTrace(printWriter);
            return printWriter.toString();
        }
    }
}
