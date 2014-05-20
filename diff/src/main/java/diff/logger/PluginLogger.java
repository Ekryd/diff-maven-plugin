package diff.logger;

/**
 * @author bjorn
 * @since 2012-12-21
 */
public interface PluginLogger {
    /**
     * Send a message to the log in the <b>warn</b> level.
     *
     * @param content warning message
     */
    void warn(String content);

    /**
     * Send a message to the log in the <b>info</b> level.
     *
     * @param content info message
     */
    void info(String content);

    /**
     * Send a message to the log in the <b>debug</b> level.
     *
     * @param content error message
     */
    void debug(String content);

    /** Return true if logger is set to debug level */
    boolean isDebug();
}
