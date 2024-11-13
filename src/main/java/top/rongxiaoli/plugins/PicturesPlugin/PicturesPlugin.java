package top.rongxiaoli.plugins.PicturesPlugin;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase;
import top.rongxiaoli.log.ElysiaLogger;

import java.util.Objects;

/**
 *
 */
public class PicturesPlugin extends JRawCommand implements PluginBase {
    /**
     * The PicturePlugin static instance.
     */
    public static final PicturesPlugin INSTANCE = new PicturesPlugin();
    private static final String NAME = "PicturesPlugin";

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
        ElysiaLogger.INSTANCE.verbose("PicturePlugin.onCommand",
                "Got message from: ",
                Objects.requireNonNull(context.getSender().getUser()).toString());
    }
    @Override
    public void load() {
        Elysia.logger.debug(NAME, "Loading " + NAME);
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
