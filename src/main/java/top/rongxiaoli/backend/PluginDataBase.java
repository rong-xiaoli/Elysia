package top.rongxiaoli.backend;

import java.util.Map;

/**
 * Basic interface of plugin data.
 * <p>
 * For each plugin data, it should be like:<br/>
 *  - StructA<br/>
 *  |---- UserIDA<br/>
 *  |   |---- SomeValueA<br/>
 *  |   |---- SomeValueB<br/>
 *  |---- UserIDB<br/>
 *  |   |---- SomeValueA<br/>
 *  |   |---- SomeValueB<br/>
 *  - StructB<br/>
 *  |---- UserIDA<br/>
 *  |   |---- SomeValueA<br/>
 *  |   |---- SomeValueB<br/>
 *  |---- UserIDB<br/>
 *  |   |---- SomeValueA<br/>
 *  |   |---- SomeValueB<br/>
 * <p>
 * This interface is the top of a single part.
 */
public interface PluginDataBase {
    // Todo: Complete plugin data.
    public static final PluginDataBase INSTANCE = null;
    public Map<Long, Object> DataObject = null;
    public void load();
    public void reload();
    public void shutdown();
    public void saveData();
}
