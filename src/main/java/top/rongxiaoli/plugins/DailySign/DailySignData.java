package top.rongxiaoli.plugins.DailySign;

import net.mamoe.mirai.console.data.java.JavaAutoSavePluginData;
import top.rongxiaoli.backend.PluginDataBase;

public class DailySignData extends JavaAutoSavePluginData implements PluginDataBase {
    public static final DailySignData INSTANCE = new DailySignData();
    private static final String NAME = "DailySign.Data";
    public DailySignData() {
        super("DailySignData");
    }

    @Override
    public void load() {
    }

    @Override
    public void reload() {
    }

    @Override
    public void shutdown() {
    }

    @Override
    public void saveData() {
    }
}
