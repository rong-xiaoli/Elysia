package top.rongxiaoli.backend.Commands;

import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.utils.MiraiLogger;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.ArisuBot;
import top.rongxiaoli.backend.PluginBase.PluginBase;

public class ArisuBotAbstractRawCommand extends JRawCommand implements PluginBase {
    private MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(ArisuBotAbstractRawCommand.class, "ArisuBot.AbstractCompositeCommand");
    public ArisuBotAbstractRawCommand(@NotNull String primaryName, @NotNull String[] secondaryNames) {
        super(ArisuBot.INSTANCE, primaryName, secondaryNames);
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
