package top.rongxiaoli.backend.PluginLoader;

import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase.PluginDataBase;
import top.rongxiaoli.plugins.DailySign.DailySignData;

import java.util.concurrent.CopyOnWriteArrayList;

public class DataLoader {
    protected CopyOnWriteArrayList<PluginDataBase> DataList;
    public static DataLoader INSTANCE = new DataLoader();
    public DataLoader() {
        this.DataList = new CopyOnWriteArrayList<>();
    }
    private void addPlugins() {
        DataList.add(DailySignData.INSTANCE);
        Elysia.INSTANCE.reloadPluginData(DailySignData.INSTANCE);
    }
    public void load() {
        for (PluginDataBase e :
                DataList) {
            e.load();
        }
    }
    public void reload() {
        for (PluginDataBase e :
                DataList) {
            e.reload();
        }
    }
    public void shutdown() {
        for (PluginDataBase e :
                DataList) {
            e.shutdown();
        }
    }
    public void saveData() {
        for (PluginDataBase e :
                DataList) {
            e.saveData();

        }
    }
}
