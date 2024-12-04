package top.rongxiaoli.backend;

import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.NudgeEvent;

/**
 * Handles any event here.
 */
public class EventListener extends SimpleListenerHost {
    @EventHandler
    public void onPoke(NudgeEvent e) {

    }
}
