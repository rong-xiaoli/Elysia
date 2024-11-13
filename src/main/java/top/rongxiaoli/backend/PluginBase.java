package top.rongxiaoli.backend;

public interface PluginBase {
    PluginBase INSTANCE = null;
    PluginConfigBase CONFIG_BASE = null;
    PluginDataBase DATA_BASE = null;

    /**
     * Load method. First time loading.
     */
    void load();

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
