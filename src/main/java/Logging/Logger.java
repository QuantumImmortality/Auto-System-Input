package Logging;

import Config.GlobalValues;

public class Logger {

    public static void writeLogMessage(String statement, GlobalValues.level logLevel) {
        System.out.println("[" + logLevel + "]: " + statement);
    }
}
