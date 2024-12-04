package top.rongxiaoli.backend.Commands;

import net.mamoe.mirai.console.command.CommandOwner;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.utils.MiraiLogger;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.backend.PluginBase.PluginBase;

public abstract class ArisuBotAbstractSimpleCommand extends JSimpleCommand implements PluginBase {
    private final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(ArisuBotAbstractSimpleCommand.class, "ArisuBot.AbstractSimpleCommand");
    public ArisuBotAbstractSimpleCommand(@NotNull CommandOwner owner, @NotNull String primaryName, @NotNull String... secondaryNames) {
        super(owner, primaryName, secondaryNames);
    }

    /**
     * Load method. First time loading.
     */
    public void load() {
    }

    /**
     * Reload method. Usually for resetting state.
     */
    public void reload() {

    }

    /**
     * Shutdown method.
     */
    public void shutdown() {

    }

    /**
     * Manually save the data.
     */
    public void saveData() {

    }

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    public void reloadData() {

    }
    @Handler
    public abstract void onCommand();
}
