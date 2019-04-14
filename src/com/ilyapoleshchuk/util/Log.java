package com.ilyapoleshchuk.util;

public class Log {

    private enum Level {
        INFO,
        ERROR,
        HEADER
    }

    public static void h(String message) {
        System.out.println();
        log(Level.HEADER, message);
        System.out.println();
    }

    public static void i(String message) {
        log(Level.INFO, message);
    }

    public static void e(String message) {
        log(Level.ERROR, message);
    }

    private static void log(Level level, String message) {
        String prettyMessage = createPrettyMessage(level, message);
        System.out.println(prettyMessage);
    }

    private static String createPrettyMessage(Level level, String message) {
        return "[" + level.toString() + "]: " + message;
    }
}
