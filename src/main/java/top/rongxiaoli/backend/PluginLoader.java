package top.rongxiaoli.backend;

import net.mamoe.mirai.console.command.CommandManager;
import top.rongxiaoli.log.ElysiaLogger;
import top.rongxiaoli.plugins.PicturesPlugin;

import java.util.concurrent.CopyOnWriteArrayList;

public class PluginLoader {
    /**
     * Plugin list.
     */
    protected CopyOnWriteArrayList<PluginBase> PluginList;
    private final CommandManager INSTANCE = CommandManager.INSTANCE;

    /**
     * Load method. First time loading. Register all plugins.
     */
    public void load() {
    }

    /**
     * Load method. Not first time loading.
     */
    public void reload() {

    }

    /**
     * Unload method.
     */
    public void shutdown() {

    }
}
