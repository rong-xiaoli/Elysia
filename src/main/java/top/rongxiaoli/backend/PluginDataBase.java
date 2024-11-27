package top.rongxiaoli.backend;

public interface PluginDataBase {
    // Todo: Complete plugin data.
    PluginDataBase INSTANCE = null;
    void load();
    void reload();
    void shutdown();
    void saveData();
}
