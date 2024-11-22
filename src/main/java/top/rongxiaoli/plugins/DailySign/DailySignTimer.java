package top.rongxiaoli.plugins.DailySign;

import net.mamoe.mirai.utils.MiraiLogger;

public class DailySignTimer implements Runnable{
    private final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(DailySignTimer.class);
    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        DailySign.clearSignCount();
        LOGGER.verbose("Sign counter cleared. ");
    }
}
