package com.example;

public class ExceptionUtil {

    public static Throwable merge(Throwable e, StackTraceElement[] stackTrace) {
        IllegalStateException throwable = new IllegalStateException(e.getMessage());
        StackTraceElement[] stackTraceElement = new StackTraceElement[e.getStackTrace().length + stackTrace.length];
        int count = 0;
        for (int i = 0; i < e.getStackTrace().length; i++) {
            stackTraceElement[count] = e.getStackTrace()[i];
            count++;
        }
        for (int i = 0; i < stackTrace.length; i++) {
            if (i == 0)
                stackTraceElement[count] = new StackTraceElement("<Thread", "start>", "", -1);
            else
                stackTraceElement[count] = stackTrace[i];
            count++;
        }
        throwable.setStackTrace(stackTraceElement);
        throwable.initCause(e.getCause());
        return throwable;
    }
}
