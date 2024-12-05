package top.rongxiaoli.backend.PluginLoader;

import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.interfaces.PluginBase.PluginDataBase;
import top.rongxiaoli.plugins.DailySign.DailySignData;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The loader of the plugin's data.
 * Detailed definitions in PluginConfigBase.java.
 */
public class ConfigLoader {
    protected CopyOnWriteArrayList<PluginDataBase> ConfigList;
    public static DataLoader INSTANCE = new DataLoader();
    // Todo: Add config loader.
    public ConfigLoader() {
        this.ConfigList = new CopyOnWriteArrayList<>();
    }
    private void addPlugins() {
        ConfigList.add(DailySignData.INSTANCE);
        ArisuBot.INSTANCE.reloadPluginData(DailySignData.INSTANCE);
    }
    public void load() {
        for (PluginDataBase e :
                ConfigList) {
            e.load();
        }
    }
    public void reload() {
        for (PluginDataBase e :
                ConfigList) {
            e.reload();
        }
    }
    public void shutdown() {
        for (PluginDataBase e :
                ConfigList) {
            e.shutdown();
        }
    }
    public void saveData() {
        for (PluginDataBase e :
                ConfigList) {
            e.saveData();

        }
    }
}
