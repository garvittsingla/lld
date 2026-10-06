package rateLimiter.models;

import java.util.LinkedList;
import java.util.Queue;

public class LeakingBucketState {
    private final Queue<Long> requests;
    private long lastLeakTime;

    public LeakingBucketState(long lastLeakTime) {
        this.requests = new LinkedList<>();
        this.lastLeakTime = lastLeakTime;
    }

    public Queue<Long> getRequests() {
        return requests;
    }

    public long getLastLeakTime() {
        return lastLeakTime;
    }

    public void setLastLeakTime(long lastLeakTime) {
        this.lastLeakTime = lastLeakTime;
    }
}
