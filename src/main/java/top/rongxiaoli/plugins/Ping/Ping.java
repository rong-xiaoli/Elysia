package top.rongxiaoli.plugins.Ping;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.CommandOwner;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase;
import top.rongxiaoli.log.ElysiaLogger;

public class Ping extends JSimpleCommand implements PluginBase {
    private ElysiaLogger logger;
    private static final String NAME = "Ping";
    public Ping(@NotNull CommandOwner owner, @NotNull String primaryName, @NotNull String... secondaryNames) {
        super(Elysia.INSTANCE, "ping");
        logger = new ElysiaLogger();
    }

    @Handler
    public void run(CommandContext context) {
        if (!Elysia.PluginRunning) {
            return;
        }
        context.getSender().sendMessage("Pong! ");
    }

    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {
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
