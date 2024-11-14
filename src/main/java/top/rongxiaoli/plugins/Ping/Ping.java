package top.rongxiaoli.plugins.Ping;

import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.backend.PluginBase;

public class Ping extends JRawCommand implements PluginBase {
    /**
     * @param context
     * @param args
     */
    @Override
    public void onCommand(@NotNull CommandContext context, @NotNull MessageChain args) {

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
