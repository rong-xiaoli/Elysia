package top.rongxiaoli.backend;

public interface PluginBase {
    PluginBase INSTANCE = null;

    /**
     * Reload method. Usually for resetting state.
     */
    void reload();

    /**
     * Shutdown method.
     */
    void shutdown();

    /**
     * Manually save the data.
     */
    void saveData();

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    void reloadData();
}
