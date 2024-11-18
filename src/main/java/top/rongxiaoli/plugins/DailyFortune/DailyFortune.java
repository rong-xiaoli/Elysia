package top.rongxiaoli.plugins.DailyFortune;

import net.mamoe.mirai.console.command.java.JSimpleCommand;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase;
import top.rongxiaoli.log.ElysiaLogger;

public class DailyFortune extends JSimpleCommand implements PluginBase {
    private final ElysiaLogger logger = new ElysiaLogger();
    public final DailyFortune INSTANCE = new DailyFortune();

    public DailyFortune() {
        super(Elysia.INSTANCE, "fortune", "yunshi", "今日运势", "jrys");
        setDescription("今日运势，今天幸运吗？");
        setPrefixOptional(false);
    }
    @Handler
    public void onCommand() {
        
    }

    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {

    }

    /**
     * Reload method. Usually for resetting state.
     */
    @Override
    public void reload() {

    }

    /**
     * Shutdown method.
     */
    @Override
    public void shutdown() {

    }

    /**
     * Manually save the data.
     */
    @Override
    public void saveData() {

    }

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    @Override
    public void reloadData() {

    }
}
