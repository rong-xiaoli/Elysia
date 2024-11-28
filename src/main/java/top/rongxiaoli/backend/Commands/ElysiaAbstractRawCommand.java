package top.rongxiaoli.backend.Commands;

import net.mamoe.mirai.console.command.java.JCompositeCommand;
import net.mamoe.mirai.utils.MiraiLogger;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.backend.PluginBase.PluginBase;

public class ElysiaAbstractRawCommand extends JCompositeCommand implements PluginBase {
    private MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(ElysiaAbstractRawCommand.class, "Elysia.AbstractCompositeCommand");
    public ElysiaAbstractRawCommand(@NotNull String primaryName, @NotNull String[] secondaryNames) {
        super(Elysia.INSTANCE, primaryName, secondaryNames);
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
