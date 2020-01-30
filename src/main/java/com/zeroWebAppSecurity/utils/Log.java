package com.zeroWebAppSecurity.utils;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.internal.Utils;

import java.sql.Timestamp;

@Getter
public class Log {
    public static final String LOG = "Log";

    private static Logger log = LoggerFactory.getLogger(LOG);
    public static StringBuilder stepsLog = new StringBuilder();
    public static StringBuilder consoleLog = new StringBuilder();
    public static StringBuilder networkLogs = new StringBuilder();
    public static final String LOG_MESSAGE_FORMAT = "{timestamp}--ThreadID:{threadId}--{LogType}: {message}";

    private static String formatLogMessage(String logType, String message) {
        return LOG_MESSAGE_FORMAT.replace("{timestamp}", getTimeStamp().toString())
                .replace("{threadId}", getThreadId().toString())
                .replace("{LogType}", logType)
                .replace("{message}", message);
    }

    public static void startTestCase(String sTestCaseName) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long threadId = Thread.currentThread().getId();

        Log.info("****************************************************************************************");

        Log.info("$$$$$$$$$$$$$$$$$$$$$     ThreadID : " + threadId + " : " + sTestCaseName + "      $$$$$$$$$$$$$$$$$$$$$$$$$");

        Log.info("****************************************************************************************");

        stepsLog.append("\n").append(formatLogMessage("Steps", " : Test with name: " + sTestCaseName + " has started"));
        consoleLog.append("\n").append(formatLogMessage("Console", " : Test with name: " + sTestCaseName + " has started"));
        networkLogs.append("\n").append(formatLogMessage("Network", " : Test with name: " + sTestCaseName + " has started"));
    }

    //This is to print log for the ending of the test case

    public static void endTestCase(String sTestCaseName) {
        Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");

        Log.info("X");

        Log.info("X");

        stepsLog.append("\n").append(formatLogMessage("Steps", " : Test with name: " + sTestCaseName + " has ended"));
        consoleLog.append("\n").append(formatLogMessage("Console", " : Test with name: " + sTestCaseName + " has ended"));
        networkLogs.append("\n").append(formatLogMessage("Network", " : Test with name: " + sTestCaseName + " has ended"));
    }

    // Need to create these methods, so that they can be called

    public static void info(String message) {
        Log.info(formatLogMessage("Info", message));
    }

    public static void warn(String message) {
        Log.warn(formatLogMessage("Warn", message));
    }

    public static void error(String message) {
        Log.warn(formatLogMessage("Error", message));
    }


    public static void debug(String message) {
        Log.debug(formatLogMessage("Debug", message));
    }

    public static void stepsToReproduce(String message) {
        Log.info(formatLogMessage("Info", message));
        stepsLog.append("\n").append(formatLogMessage("Steps", message));
        consoleLog.append("\n").append(formatLogMessage("Console", message));
        networkLogs.append("\n").append(formatLogMessage("Network", message));
    }

    public static void assertion(String message) {
        Log.info(formatLogMessage("Assertion", message));
        stepsLog.append("\n").append(formatLogMessage("Assertion", message));
    }

    public static void writeConsoleLog(String message) {
        Log.info(formatLogMessage("Console", message));
        consoleLog.append("\n").append(formatLogMessage("Console", message));
    }

    public static void writeNetworkLog(String message) {
//        Log.info(formatLogMessage("Network", message));
        networkLogs.append("\n").append(formatLogMessage("Network", message));
    }

    public static StringBuilder getStepsLog() {
        return stepsLog;
    }

    public static StringBuilder getConsoleLog() {
        return consoleLog;
    }

    public static StringBuilder getNetworkLogs() {
        return networkLogs;
    }

    public static void cleanTestSuitLog() {
        stepsLog = new StringBuilder();
    }

    public static void cleanConsoleLog() {
        consoleLog = new StringBuilder();
    }

    public static void cleanNetworkLog() {
        networkLogs = new StringBuilder();
    }

    private static Long getThreadId() {
        return Thread.currentThread().getId();
    }

    private static Timestamp getTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
