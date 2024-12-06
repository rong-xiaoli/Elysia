package top.rongxiaoli.backend.interfaces.PluginBase;

/**
 * Basic interface of a config.
 */
public interface PluginConfigBase {
    PluginConfigBase INSTANCE = null;
    void load();
    void reload();
    void shutdown();
    void saveData();
}
