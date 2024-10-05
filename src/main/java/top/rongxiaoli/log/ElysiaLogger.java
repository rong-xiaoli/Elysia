package top.rongxiaoli.log;

import net.mamoe.mirai.utils.MiraiLogger;

/**
 * Elysia logger.
 */
public class ElysiaLogger {
    public static ElysiaLogger INSTANCE;
    private static MiraiLogger MIRAI_LOGGER_INSTANCE;

    public static void setLogger(MiraiLogger logger) {
        ElysiaLogger.MIRAI_LOGGER_INSTANCE = logger;
    }

    /**
     * Send a verbose message.
     *
     * @param pluginName Plugin register name.
     * @param message    Info message.
     */
    public void verbose(String pluginName, String... message) {
        StringBuilder messageBuilder = new StringBuilder();
        for (String element :
                message) {
            messageBuilder.append(element);
        }
        MIRAI_LOGGER_INSTANCE.verbose("{" + pluginName + "} " + messageBuilder);
    }

    /**
     * Send a debug message.
     *
     * @param pluginName Plugin register name.
     * @param message    Info message.
     */
    public void debug(String pluginName, String... message) {
        StringBuilder messageBuilder = new StringBuilder();
        for (String element :
                message) {
            messageBuilder.append(element);
        }
        MIRAI_LOGGER_INSTANCE.debug("{" + pluginName + "} " + messageBuilder);
    }

    /**
     * Send an info message.
     *
     * @param pluginName Plugin register name.
     * @param message    Info message.
     */
    public void info(String pluginName, String... message) {
        StringBuilder messageBuilder = new StringBuilder();
        for (String element :
                message) {
            messageBuilder.append(element);
        }
        MIRAI_LOGGER_INSTANCE.info("{" + pluginName + "} " + messageBuilder);
    }

    /**
     * Send a warning message.
     *
     * @param pluginName Plugin register name.
     * @param message    Info message.
     */
    public void warn(String pluginName, String... message) {
        StringBuilder messageBuilder = new StringBuilder();
        for (String element :
                message) {
            messageBuilder.append(element);
        }
        MIRAI_LOGGER_INSTANCE.warning("{" + pluginName + "} " + messageBuilder);
    }

    /**
     * Send an error message.
     *
     * @param pluginName Plugin register name.
     * @param message    Info message.
     */
    public void error(String pluginName, String... message) {
        StringBuilder messageBuilder = new StringBuilder();
        for (String element :
                message) {
            messageBuilder.append(element);
        }
        MIRAI_LOGGER_INSTANCE.error("{" + pluginName + "} " + messageBuilder);
    }

}
