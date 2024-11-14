package top.rongxiaoli;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import top.rongxiaoli.backend.PluginLoader;
import top.rongxiaoli.log.ElysiaLogger;

import java.nio.file.Path;


public final class Elysia extends JavaPlugin {
    public static final Elysia INSTANCE = new Elysia();
    public static ElysiaLogger logger;
    public static final PluginLoader LOADER = new PluginLoader();
    public static Path ConfigPath = INSTANCE.getConfigFolderPath();
    public static Path DataPath = INSTANCE.getDataFolderPath();
    public static boolean PluginRunning = false;

    private Elysia() {
        super(new JvmPluginDescriptionBuilder("top.rongxiaoli.elysia", "0.0.0")
                .name("Elysia")
                .info("REBORN, even better. ")
                .author("rong-xiaoli")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().verbose("Plugin loading, packaged logger initialization started.");
        logger = new ElysiaLogger();
        ElysiaLogger.setLogger(getLogger());
        logger.verbose("Elysia.onEnable", "Logger initialization complete. ");
        LOADER.load();
        logger.verbose("Elysia.onEnable", "Initialization complete. ");
        Elysia.PluginRunning = true;
    }
}