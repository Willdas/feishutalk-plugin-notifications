package io.jenkins.plugins.feishu.util;

import hudson.model.TaskListener;
import org.apache.commons.lang.StringUtils;

import java.io.PrintStream;

import static io.jenkins.plugins.feishu.contants.NotifyConstants.LOG_TITLE;

public class LoggerUtil {

    /**
     * 日志 icon
     */
    private static final String LOG_ICON = "\u2708\ufe0f";

    /**
     * 日志连接线符号
     */
    private static final String LOG_LINE_SYMBOL = "\uD83C\uDF2B";
    /**
     * 连接线单边的长度
     */
    private static final short LOG_LINE_HALF_COUNT = 12;

    /**
     * 单条连接线
     */
    private static final String LOG_HALF_LINE = StringUtils.repeat(LOG_LINE_SYMBOL, LOG_LINE_HALF_COUNT);

    /**
     * 标题占位符
     */
    private static final String LOG_TITLE_PLACEHOLDER = StringUtils.repeat(LOG_LINE_SYMBOL, LOG_TITLE.length());

    /**
     * 统一输出错误日志
     *
     * @param listener 任务监听器
     * @param msg      消息
     */
    public static void error(TaskListener listener, String msg) {
        listener.error("飞书机器人发生错误：%s", msg);
    }

    /**
     * 统一输出调试日志
     *
     * @param listener 任务监听器
     * @param msg      消息
     */
    public static void info(TaskListener listener, String msg) {
        PrintStream logger = listener.getLogger();
        logger.println();
        logger.println(msg);
    }

    /**
     * 分割线
     *
     * @param listener 任务监听器
     * @param lineType 线的类型
     */
    public static void line(TaskListener listener, LineType lineType) {
        PrintStream logger = listener.getLogger();
        if (LineType.START.equals(lineType)) {
            logger.println();
            logger.println(LOG_ICON + LOG_HALF_LINE + LOG_TITLE + LOG_HALF_LINE + LOG_ICON);
        } else {
            logger.println(LOG_ICON + LOG_HALF_LINE + LOG_TITLE_PLACEHOLDER + LOG_HALF_LINE + LOG_ICON);
            logger.println();
        }
    }

    public enum LineType {
        /**
         * 开始
         */
        START,
        /**
         * 结束
         */
        END;
    }
}
