package top.rongxiaoli.backend;

import net.mamoe.mirai.console.command.CommandManager;
import top.rongxiaoli.Elysia;
import top.rongxiaoli.plugins.PicturesPlugin.PicturesPlugin;
import top.rongxiaoli.plugins.Ping.Ping;

import java.util.concurrent.CopyOnWriteArrayList;

public class PluginLoader {
    /**
     * Plugin list.
     */
    protected CopyOnWriteArrayList<PluginBase> PluginList;
    private final CommandManager INSTANCE = CommandManager.INSTANCE;
    public PluginLoader() {
        this.PluginList = new CopyOnWriteArrayList<>();
    }

    /**
     * Load method. First time loading. Register all plugins.
     */
    public void load() {
        addPlugins();
        for (PluginBase e :
                PluginList) {
            e.load();
        }
        registerCommand();
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
    private void addPlugins() {
        PluginList.add(PicturesPlugin.INSTANCE);
    }
    private void registerCommand() {
        this.INSTANCE.registerCommand(PicturesPlugin.INSTANCE, false);
        Elysia.logger.debug("PluginLoader.addPlugins", "Added PicturesPlugin. ");
        this.INSTANCE.registerCommand(Ping.INSTANCE, false);
        Elysia.logger.debug("PluginLoader.addPlugins", "Added Ping. ");
    }
}
