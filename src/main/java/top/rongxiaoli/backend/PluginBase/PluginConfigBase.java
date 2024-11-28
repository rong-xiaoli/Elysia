package top.rongxiaoli.backend.PluginBase;

import java.util.HashMap;

/**
 * Basic interface of a config.
 */
public abstract class PluginConfigBase {
    HashMap<String, Object> configHashMap = new HashMap<>();

    /**
     * Default config reader.
     * @param configName Config name.
     */
    public Object readConfig(String configName) throws IllegalArgumentException {
        return configHashMap.get(configName);
    }
    public void writeConfig(String configName, Object configObject) {
        configHashMap.put(configName, configObject);
    }
}
