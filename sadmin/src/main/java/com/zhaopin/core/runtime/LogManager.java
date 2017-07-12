package com.zhaopin.core.runtime;

import com.zhaopin.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhou.hao on 2017/7/12.
 */
public class LogManager {
    private static final Logger logger = LoggerFactory.getLogger(LogManager.class);

    public static void debug(Class<?> type, String message) {
        logger.debug(String.format("%s info:%s", type.getSimpleName(), message));
    }

    public static void debug(Class<?> type, String message, String... args) {
        logger.debug(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")));
    }

    public static void debug(Class<?> type, String message, Throwable e) {
        logger.debug(String.format("%s info:%s", type.getSimpleName(), message), e);
    }

    public static void debug(Class<?> type, String message, Throwable e, String... args) {
        logger.debug(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")), e);
    }

    public static void info(Class<?> type, String message) {
        logger.info(String.format("%s info:%s", type.getSimpleName(), message));
    }

    public static void info(Class<?> type, String message, String... args) {
        logger.info(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")));
    }

    public static void info(Class<?> type, String message, Throwable e) {
        logger.info(String.format("%s info:%s", type.getSimpleName(), message), e);
    }

    public static void info(Class<?> type, String message, Throwable e, String... args) {
        logger.info(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")), e);
    }

    public static void warn(Class<?> type, String message) {
        logger.warn(String.format("%s info:%s", type.getSimpleName(), message));
    }

    public static void warn(Class<?> type, String message, String... args) {
        logger.warn(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")));
    }

    public static void warn(Class<?> type, String message, Throwable e) {
        logger.warn(String.format("%s info:%s", type.getSimpleName(), message), e);
    }

    public static void warn(Class<?> type, String message, Throwable e, String... args) {
        logger.warn(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")), e);
    }

    public static void error(Class<?> type, String message) {
        logger.warn(String.format("%s info:%s", type.getSimpleName(), message));
    }

    public static void error(Class<?> type, String message, String... args) {
        logger.warn(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")));
    }

    public static void error(Class<?> type, String message, Throwable e) {
        logger.warn(String.format("%s info:%s", type.getSimpleName(), message), e);
    }

    public static void error(Class<?> type, String message, Throwable e, String... args) {
        logger.warn(String.format("%s info: %s args: %s", type.getSimpleName(), message, StringUtil.joinString(args, " ")), e);
    }
}
