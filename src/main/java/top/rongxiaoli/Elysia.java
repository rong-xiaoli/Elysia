package top.rongxiaoli;

import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;
import top.rongxiaoli.backend.PluginLoader.DataLoader;
import top.rongxiaoli.backend.PluginLoader.PluginLoader;

import java.nio.file.Path;


public final class Elysia extends JavaPlugin {
    public static final Elysia INSTANCE = new Elysia();
    public static final PluginLoader LOADER = new PluginLoader();
    public static final DataLoader DATA = new DataLoader();
    public static boolean PluginRunning = false;

    private Elysia() {
        super(new JvmPluginDescriptionBuilder("top.rongxiaoli.elysia", "0.0.0")
                .name("Elysia")
                .info("REBORN, even better. ")
                .author("rong-xiaoli")
                .build());
    }

    /**
     * @param $this$onLoad This parameter is not used. 
     */
    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        getLogger().debug("Loading Elysia plugin data...");
        DATA.load();
        getLogger().debug("Load complete. Waiting for enabling. ");
    }

    @Override
    public void onEnable() {
        getLogger().verbose("Plugin loading.");
        LOADER.load();
        getLogger().verbose("Plugin load complete. ");
        getLogger().verbose("Initialization complete. ");
        Elysia.PluginRunning = true;
    }

    @Override
    public void onDisable() {
        getLogger().debug("Start disabling process. ");
        LOADER.shutdown();
        getLogger().debug("Shutdown complete. ");
    }

    public static Path GetConfigPath() {
      return INSTANCE.getConfigFolderPath();
    }
    public static Path GetDataPath() {
      return INSTANCE.getDataFolderPath();
    }
}