package top.rongxiaoli.plugins.DailySign;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.MiraiLogger;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class DailySign extends JSimpleCommand implements PluginBase {
    private static final String bingPictAPI = "https://bing.img.run/1366x768.php";
    private static final DailySignData DATA = new DailySignData();
    private static final MiraiLogger logger = MiraiLogger.Factory.INSTANCE.create(DailySign.class);
    public static final DailySign INSTANCE = new DailySign();
    public DailySign() {
        super(Elysia.INSTANCE, "sign", "qd");
        setDescription("每日签到");
    }
    @Handler
    public void onCommand(CommandContext context) {
        // Not console.
        if (isConsoleCalling(context)) {
            context.getSender().sendMessage("你是0吗？");
            return;
        }
        long userID = Objects.requireNonNull(context.getSender().getUser()).getId();
        MessageChainBuilder mainBuilder = new MessageChainBuilder();
        DailySignData.DailySignPersonData userData = DATA.query(userID);
        GregorianCalendar gCalendar = ((GregorianCalendar) Calendar.getInstance());
        if (gCalendar.get(Calendar.DAY_OF_YEAR) == userData.lastLoginDate.get(Calendar.DAY_OF_YEAR)) {
            mainBuilder.append("你已经签过到了哦~\n");
        }

    }
    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {
        logger.debug("DailySign loading. ");
        DATA.load();
        logger.verbose("Data load complete. ");
        logger.verbose("No config load needed. ");
        logger.debug("DailySign loaded. ");
    }

    /**
     * Reload method. Usually for resetting state.
     */
    @Override
    public void reload() {
        logger.debug("DailySign reloading. ");
        DATA.reload();
        logger.verbose("Data load complete. ");
        logger.verbose("No config reload needed. ");
        logger.debug("DailySign reloaded. ");
    }

    /**
     * Shutdown method.
     */
    @Override
    public void shutdown() {
        logger.debug("DailySign shutting down. ");
        DATA.shutdown();
        logger.verbose("Data shutdown complete. ");
        logger.verbose("No config shutdown needed. ");
        logger.debug("DailySign shut down. ");
    }

    /**
     * Manually save the data.
     */
    @Override
    public void saveData() {
        logger.debug("DailySign reloading. ");
        DATA.saveData();
        logger.verbose("Data saved. ");
    }

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    @Override
    public void reloadData() {
        logger.debug("DailySign data reloading. ");
        DATA.reload();
        logger.verbose("Data reload complete. ");
        logger.verbose("No config needed. ");
        logger.debug("DailySign reloaded. ");
    }
    private boolean isConsoleCalling(CommandContext context) {
        long userID = 0, subjectID = 0;
        // From console, return:
        try {
            userID = Objects.requireNonNull(context.getSender().getUser()).getId();
            subjectID = Objects.requireNonNull(context.getSender().getSubject()).getId();
        } catch (NullPointerException e) {
            logger.warning("This command cannot be invoked from console! ");
            return true;
        }
        if (userID == 0 || subjectID == 0) {
            logger.warning("This command cannot be invoked from console! ");
            return true;
        }
        return false;
    }

}
