package top.rongxiaoli.backend;

import net.mamoe.mirai.utils.MiraiLogger;

public interface PluginBase {
    PluginBase INSTANCE = null;
    PluginConfigBase CONFIG_BASE = null;
    PluginDataBase DATA_BASE = null;
    MiraiLogger LOGGER = null;

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
