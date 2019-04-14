package com.ilyapoleshchuk.util;

public class Log {

    private enum Level {
        INFO,
        ERROR
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
