package top.rongxiaoli.plugins.DailySign;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.utils.MiraiLogger;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.PluginBase.PluginBase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailySign extends JSimpleCommand implements PluginBase {
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
    private static int signCount = 0;
    private static final DailySignData DATA = new DailySignData();
    private static final MiraiLogger logger = MiraiLogger.Factory.INSTANCE.create(DailySign.class, "ArisuBot.DailySign");
    public static final DailySign INSTANCE = new DailySign();
    public static void clearSignCount() {
        signCount = 0;
    }
    public DailySign() {
        super(ArisuBot.INSTANCE, "sign", "qd");
        setDescription("每日签到");
    }
    @Handler
    public void onCommand(CommandContext context) {
        // Is console calling?
        if (isConsoleCalling(context)) {
            context.getSender().sendMessage("你是0吗？");
            return;
        }
        long userID = Objects.requireNonNull(context.getSender().getUser()).getId();
        MessageChainBuilder mainBuilder = new MessageChainBuilder();
        GregorianCalendar lastSign = (GregorianCalendar) Calendar.getInstance();
        long lastSignMillis = DATA.queryLastSignDate(userID);
        lastSign.setTimeInMillis(lastSignMillis);
        int signCombo = DATA.querySignCombo(userID);
        GregorianCalendar gCalendar = ((GregorianCalendar) Calendar.getInstance());
        logger.verbose(String.valueOf(gCalendar.get(Calendar.DAY_OF_YEAR)));
        logger.verbose(String.valueOf(lastSign.get(Calendar.DAY_OF_YEAR)));
        if (gCalendar.get(Calendar.DAY_OF_YEAR) == lastSign.get(Calendar.DAY_OF_YEAR)) {
            mainBuilder.append("你已经签过到了哦~\n");
            mainBuilder.append(DailySignString.GetRandomString());
            context.getSender().sendMessage(mainBuilder.build());
            return;
        }
        GregorianCalendar newSign = ((GregorianCalendar) Calendar.getInstance());
        int newCombo;
        if (newSign.getTimeInMillis() - lastSign.getTimeInMillis() >= 86400) {
            newCombo = 1;
        } else newCombo = signCombo + 1;
        signCount += 1;
        DATA.setLastSignDate(userID, newSign.getTimeInMillis());
        DATA.setSignCombo(userID, newCombo);
        mainBuilder.append("签到咯~\n");
        mainBuilder.append(DailySignString.GetRandomString()).append("\n")
                .append("你已连续签到").append(String.valueOf(newCombo)).append("天\n")
                .append("今天你是第").append(String.valueOf(signCount)).append("个签到的");
        context.getSender().sendMessage(mainBuilder.build());
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
        executorService.scheduleAtFixedRate(
                new DailySignTimer.SignCountTimer(),
                getMilliSecondsToNextDay12AM(),
                86400000000L,
                TimeUnit.MILLISECONDS
        );
        executorService.scheduleAtFixedRate(
                new DailySignTimer.DataSaveTimer(),
                0,
                5,
                TimeUnit.MINUTES
        );
        logger.verbose("The two scheduler started. ");
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
        executorService.shutdown();
        logger.debug("DailySign shut down. ");
    }

    /**
     * Manually save the data.
     */
    @Override
    public void saveData() {
        logger.debug("Saving data. ");
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
    private long getMilliSecondsToNextDay12AM () {
        Calendar target = Calendar.getInstance();
        target.add(Calendar.DAY_OF_YEAR, 1);
        target.set(Calendar.HOUR_OF_DAY, 0);
        target.set(Calendar.MINUTE, 0);
        target.set(Calendar.SECOND, 0);
        target.set(Calendar.MILLISECOND, 0);
        return target.getTimeInMillis() - System.currentTimeMillis();
    }

}
