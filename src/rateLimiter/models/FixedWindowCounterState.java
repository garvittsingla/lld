package rateLimiter.models;

import java.util.HashMap;

public class FixedWindowCounterState {
    private long windowStart;
    private int counter;

    public FixedWindowCounterState(long windowStart){
        this.windowStart = windowStart;
        this.counter  = 0;
    }

    public int getCounter() {
        return counter;
    }

    public long getWindowStart() {
        return windowStart;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setWindowStart(long windowStart) {
        this.windowStart = windowStart;
    }
}
