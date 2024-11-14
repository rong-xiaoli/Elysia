package top.rongxiaoli.plugins.PicturesPlugin;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.MiraiLogger;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase;
import top.rongxiaoli.log.ElysiaLogger;

import java.util.Objects;

/**
 * Picture plugin.
 */
public class PicturesPlugin extends JRawCommand implements PluginBase {
    public ElysiaLogger logger = new ElysiaLogger();
    /**
     * The PicturePlugin static instance.
     */
    public static final PicturesPlugin INSTANCE = new PicturesPlugin();
    private static final String NAME = "PicturesPlugin";
    private DelayedDisposer disposer;

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
        logger.verbose(NAME, "Command invoked by " + context.getSender().getUser() + ", on context: " + context.getSender().getSubject().getId());
    }

    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {
        logger.debug(NAME, "Command loading. ");
        logger.verbose(NAME, "Initializing Disposer. ");
        this.disposer = new DelayedDisposer();
        logger.debug(NAME, "Command loaded. ");
    }

    /**
     * Reload method. Usually for resetting state.
     */
    @Override
    public void reload() {
        logger.debug(NAME, "Reload complete. ");
    }

    /**
     * Shutdown method.
     */
    @Override
    public void shutdown() {
        logger.debug(NAME, "shutdown() invoked. Nothing special, pass. ");
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
