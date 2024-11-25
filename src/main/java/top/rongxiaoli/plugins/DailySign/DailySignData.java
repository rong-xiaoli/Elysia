package top.rongxiaoli.plugins.DailySign;

import net.mamoe.mirai.console.data.Value;
import net.mamoe.mirai.console.data.java.JavaAutoSavePluginData;
import net.mamoe.mirai.utils.MiraiLogger;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginDataBase;

import java.util.HashMap;
import java.util.Map;
public class DailySignData extends JavaAutoSavePluginData implements PluginDataBase {
    public static final DailySignData INSTANCE = new DailySignData();
    private static final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(DailySignData.class, "Elysia.DailySign.Data");
    public DailySignData() {
        super("DailySignData");
    }
    private final Value<Map<Long, Long>> lastSignDateDataset = typedValue("SignDateMap",
            createKType(
                    Map.class,
                    createKType(Long.class),
                    createKType(Long.class)
            ),
            new HashMap<Long, Long>() {{
                put(1L, 0L);
            }}
            );
    private final Value<Map<Long, Integer>> signComboDataSet = typedValue("SignComboMap",
            createKType(
                    Map.class,
                    createKType(Long.class),
                    createKType(int.class)
            ),
            new HashMap<Long, Integer>() {{
                put(1L, 0);
            }});
    @Override
    public void load() {
        LOGGER.verbose("Loading data. ");
        Elysia.INSTANCE.reloadPluginData(INSTANCE);
        LOGGER.debug("Load complete. ");
    }

    @Override
    public void reload() {
        LOGGER.debug("Start reloading data. ");
        Elysia.INSTANCE.reloadPluginData(INSTANCE);
        LOGGER.debug("Data reloading complete. ");
    }

    @Override
    public void shutdown() {
        LOGGER.debug("Start shutdown process. ");
        Elysia.INSTANCE.savePluginData(INSTANCE);
        LOGGER.debug("Shutdown process complete. ");
    }

    @Override
    public void saveData() {
        LOGGER.debug("Saving data. ");
        Elysia.INSTANCE.savePluginData(INSTANCE);
        LOGGER.debug("Data saved. ");
    }
    public long queryLastSignDate(long userID) {
        Map<Long, Long> temp = lastSignDateDataset.get();
        if(temp.get(userID) == null) return 0L;
        return temp.get(userID);
    }
    public int querySignCombo(long userID) {
        Map<Long, Integer> temp = signComboDataSet.get();
        if (temp.get(userID) == null) return 0;
        return temp.get(userID);
    }
    public void setLastSignDate(long userID, long date) {
        Map<Long,Long> temp = lastSignDateDataset.get();
        if (temp.containsKey(userID)) temp.replace(userID, date);
        else temp.put(userID, date);
        lastSignDateDataset.set(temp);
    }
    public void setSignCombo(long userID, int count) {
        Map<Long,Integer> temp = signComboDataSet.get();
        if (temp.containsKey(userID)) temp.replace(userID, count);
        else temp.put(userID, count);
        signComboDataSet.set(temp);
    }
}
