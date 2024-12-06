package top.rongxiaoli.plugins.Ping;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.utils.MiraiLogger;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.interfaces.PluginBase.PluginBase;

public class Ping extends JSimpleCommand implements PluginBase {
    private final MiraiLogger logger = MiraiLogger.Factory.INSTANCE.create(Ping.class, "ArisuBot.Ping");
    public static final Ping INSTANCE = new Ping();
    public Ping() {
        super(ArisuBot.INSTANCE, "ping");
        setPrefixOptional(true);
    }

    @Handler
    public void run(CommandContext context) {
        if (!ArisuBot.PluginRunning) {
            return;
        }
        context.getSender().sendMessage("Pong! ");
    }

    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {logger.debug("Command loaded. ");
    }

    /**
     * Reload method. Usually for resetting state.
     */
    @Override
    public void reload() {
        logger.debug("Reload complete. ");
    }

    /**
     * Shutdown method.
     */
    @Override
    public void shutdown() {
        logger.debug("shutdown() invoked. Nothing special, pass. ");
    }

    /**
     * Manually save the data.
     */
    @Override
    public void saveData() {
        logger.debug("Nothing to store. ");
    }

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    @Override
    public void reloadData() {
        logger.debug("Nothing to load. ");
    }
}
