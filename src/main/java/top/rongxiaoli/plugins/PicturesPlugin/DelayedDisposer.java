package top.rongxiaoli.plugins.PicturesPlugin;

import net.mamoe.mirai.contact.User;
import org.jetbrains.annotations.NotNull;

import javax.management.InstanceAlreadyExistsException;
import java.util.LinkedHashSet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedDisposer {
    public DelayedDisposer() {
        this.coolingQueue = new DelayQueue<>();
    }
    private final DelayQueue<CoolingUser> coolingQueue;
    private LinkedHashSet<Long> userHashSet;
    /**
     * Add user into cooling list using default cooling timer.
     * @param user User object.
     */
    public void AddUser(User user) throws ElementAlreadyExistsException {
        if (userHashSet.contains(user.getId())) {
            throw new ElementAlreadyExistsException(user);
        }
        userHashSet.add(user.getId());
        coolingQueue.add(new CoolingUser(user.getId()));
    }

    /**
     * Add user into cooling list.
     * @param user User object.
     * @param intervalSecond Time user to be cooled. Time unit is second.
     */
    public void AddUser(User user, int intervalSecond) throws ElementAlreadyExistsException {
        if (userHashSet.contains(user.getId())) {
            throw new ElementAlreadyExistsException(user);
        }
        userHashSet.add(user.getId());
        coolingQueue.add(new CoolingUser(user.getId(), ((long) intervalSecond) * 1000000));
    }
    public static class CoolingUser implements Delayed {
        private final long user;
        private final long availableTime;
        public CoolingUser(long user, long delayedTime) {
            this.user = user;
            this.availableTime = delayedTime + System.currentTimeMillis();
        }
        public CoolingUser(long user) {
            this.user = user;
            this.availableTime = System.currentTimeMillis() + 60000000L;
        }
        public long getAvailableTime() {return availableTime;}

        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            long delta = availableTime - System.currentTimeMillis();
            return unit.convert(delta, TimeUnit.MILLISECONDS);
        }
        @Override
        public int compareTo(@NotNull Delayed o) {
            return (int) (this.availableTime - ((CoolingUser)o).getAvailableTime());
        }
    }
    public static class ElementAlreadyExistsException extends Exception {
        private final Object obj;
        public ElementAlreadyExistsException(Object obj) {
            this.obj = obj;
        }
        public ElementAlreadyExistsException(Object obj, String message) {
            super(message);
            this.obj = obj;
        }
        public Object getObj() {return obj;}
    }
}
