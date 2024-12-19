package top.rongxiaoli.backend.Commands;

import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.utils.MiraiLogger;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.interfaces.PluginBase.PluginBase;

public abstract class ArisuBotAbstractRawCommand extends JRawCommand implements PluginBase {
    private MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(ArisuBotAbstractRawCommand.class, "ArisuBot.AbstractCompositeCommand");
    public ArisuBotAbstractRawCommand(@NotNull String primaryName, @NotNull String[] secondaryNames) {
        super(ArisuBot.INSTANCE, primaryName, secondaryNames);
    }

    /**
     * Load method. First time loading.
     */
    @Override
    public void load() {
        LOGGER.debug("Plugin loading. ");
        LOGGER.debug("Plugin loaded. ");
    }

    /**
     * Reload method. Usually for resetting state.
     */
    @Override
    public void reload() {
        LOGGER.debug("Plugin reloading. ");
        LOGGER.debug("Plugin reloaded. ");
    }

    /**
     * Shutdown method.
     */
    @Override
    public void shutdown() {
        LOGGER.debug("Plugin shutting down. ");
        LOGGER.debug("Plugin shut down. ");
    }

    /**
     * Manually save the data.
     */
    @Override
    public void saveData() {
        LOGGER.debug("Data saving. ");
        LOGGER.debug("Data saved. ");
    }

    /**
     * Manually reload the data. Discard the changes in memory.
     */
    @Override
    public void reloadData() {
        LOGGER.debug("Data reloading. ");
        LOGGER.debug("Data reloaded. ");
    }
}
