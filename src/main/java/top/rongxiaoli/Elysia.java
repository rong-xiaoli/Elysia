package top.rongxiaoli;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import top.rongxiaoli.backend.PluginLoader;
import top.rongxiaoli.log.ElysiaLogger;


public final class Elysia extends JavaPlugin {
    public static final Elysia INSTANCE = new Elysia();
    public static ElysiaLogger logger;
    public static final PluginLoader LOADER = new PluginLoader();

    private Elysia() {
        super(new JvmPluginDescriptionBuilder("top.rongxiaoli.elysia", "0.0.0")
                .name("Elysia")
                .info("REBORN")
                .author("rong-xiaoli")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().verbose("Plugin loading, packaged logger initialization started.");
        logger = new ElysiaLogger();
        ElysiaLogger.setLogger(getLogger());
        logger.verbose("Elysia.onEnable", "Initialization complete. ");
    }
}