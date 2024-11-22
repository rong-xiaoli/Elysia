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
    private static final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(DailySignData.class);
//    public GregorianCalendar lastLoginDate;
//    public int ContinuousSignCombo;
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
        if(lastSignDateDataset.get().get(userID) == null) return 0L;
        return lastSignDateDataset.get().get(userID);
    }
    public void setLastSignDate(long userID, long date) {
        if (!lastSignDateDataset.get().containsKey(userID)) lastSignDateDataset.get().replace(userID, date);
        else lastSignDateDataset.get().put(userID, date);
    }
    public int querySignCombo(long userID) {
        if (signComboDataSet.get().get(userID) == null) return 0;
        return signComboDataSet.get().get(userID);
    }
    public void setSignCombo(long userID, int count) {
        if (!signComboDataSet.get().containsKey(userID)) signComboDataSet.get().replace(userID, count);
        else signComboDataSet.get().put(userID, count);
    }
}
