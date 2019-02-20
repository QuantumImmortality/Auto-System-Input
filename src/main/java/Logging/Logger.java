package Logging;

public class Logger {

    public static void writeLogMessage(String statement, LogLevel.level logLevel) {
        System.out.println("[" + logLevel + "]: " + statement);
    }
}
