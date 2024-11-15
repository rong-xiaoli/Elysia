package top.rongxiaoli.plugins.PicturesPlugin;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase;
import top.rongxiaoli.log.ElysiaLogger;

import java.util.Objects;

/**
 * Picture plugin.
 */
public class PicturesPlugin extends JRawCommand implements PluginBase {
    private final ElysiaLogger logger = new ElysiaLogger();
    /**
     * The PicturePlugin static instance.
     */
    public static final PicturesPlugin INSTANCE = new PicturesPlugin();
    private static final String NAME = "PicturesPlugin";
    private DelayedDisposer disposer;
    private static boolean isPluginRunning = false;

    /**
     * Pictures from lolicon API.
     */
    public PicturesPlugin() {
        super(Elysia.INSTANCE, "setu");
        setUsage("[/]setu [keyword1 keyword2 keyword3 ...]");
        setDescription("涩图，使用Lolicon API，可指定关键词");
        setPrefixOptional(true);
    }

    /**
     * PicturePlugin processing main method.
     */
    @Override
    public void onCommand(@NotNull CommandContext context, @NotNull MessageChain args) {
        // Plugin running?
        if (!isPluginRunning) {
            context.getSender().sendMessage("已禁用该插件");
            return;
        }
        long userID = 0, subjectID = 0;
        boolean isDirectMessaging = false;
        // From console:
        try {
            userID = Objects.requireNonNull(context.getSender().getUser()).getId();
            subjectID = Objects.requireNonNull(context.getSender().getSubject()).getId();
        } catch (NullPointerException e) {
            logger.warn(NAME, "This command cannot be invoked from console! ");
        }
        if (userID == 0 || subjectID == 0) {
            logger.warn(NAME, "This command cannot be invoked from console! ");
            return;
        }
        // Is DM?
        if (userID == subjectID) {
            isDirectMessaging = true;
        }
        // From user:
        logger.verbose(NAME, "Command invoked by " + context.getSender().getUser().getId()
                + ", on context: " + (context.getSender().getSubject() == null ? " " : context.getSender().getSubject().getId()));
        // Try adding user into cooling queue.
        try {
            disposer.AddUser(Objects.requireNonNull(context.getSender().getUser()));
            // User already cooling.
        } catch (DelayedDisposer.ElementAlreadyExistsException e) {
            // Reject request.
            long time = disposer.QueryCoolingTime(Objects.requireNonNull(context.getSender().getUser()));
            if (isDirectMessaging) {
                context.getSender().sendMessage("请等待冷却：" + time + "秒");   // Todo: Use reply instead of directly say sth.
            }
            context.getSender().sendMessage(new At(userID).plus("请等待冷却：" + time + "秒"));   // Todo: Use reply instead of directly say sth.
            return;
        }
        // Processing request.
        if (isDirectMessaging) {
            context.getSender().sendMessage(new At(userID).plus("正在处理你的消息"));
        }
        context.getSender().sendMessage(new At(userID).plus("正在处理你的消息"));
//        Path targetPath = Elysia.GetDataPath().getFileSystem().getPath("PictureFolder");
        // Todo: Implementation.
    }

    public boolean getRunningStatus() {
        return isPluginRunning;
    }

    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {
        logger.debug(NAME, "Command loading. ");
        logger.verbose(NAME, "Initializing Disposer. ");
        this.disposer = new DelayedDisposer();
        disposer.startTiming();
        isPluginRunning = true;
        logger.debug(NAME, "Command loaded. ");
    }

    /**
     * Reload method. Usually for resetting state.
     */
    @Override
    public void reload() {
        logger.debug(NAME, "Reloading. ");
        disposer = new DelayedDisposer();
        logger.debug(NAME, "Reload complete. ");
    }

    /**
     * Shutdown method.
     */
    @Override
    public void shutdown() {
        logger.debug(NAME, "shutdown() invoked.");
        disposer.Shutdown();
        isPluginRunning = false;
        logger.debug(NAME, "Shut down.");
    }

    /**
     * Manually save the data.
     */
    @Override
    public void saveData() {
        logger.debug(NAME, "Nothing to store. ");
    }

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    @Override
    public void reloadData() {
        logger.debug(NAME, "Nothing to load. ");
    }
}
