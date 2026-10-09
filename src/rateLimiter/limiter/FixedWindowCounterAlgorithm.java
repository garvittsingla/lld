package rateLimiter.limiter;

import rateLimiter.models.FixedWindowCounterState;
import rateLimiter.models.RateLimitConfig;
import rateLimiter.models.User;

import java.util.HashMap;

public class FixedWindowCounterAlgorithm implements RateLimiter{
    HashMap<User, FixedWindowCounterState> windows;
    RateLimitConfig config;

    public FixedWindowCounterAlgorithm(RateLimitConfig config){
        this.windows = new HashMap<>();
        this.config = config;
    }

    @Override
    public boolean allowRequest(User user) {
       long now = System.currentTimeMillis();

       double second = (double)now /1000.00;

       FixedWindowCounterState window = windows.computeIfAbsent(
               user,
               user1 -> new FixedWindowCounterState(
                       now
               )
       );

       long lastWindowStart = window.getWindowStart();
       double lastWindowStartSeconds = (double)lastWindowStart / 1000.00;

       double desiredWindow = config.getWindowSeconds();
       int maxRequests = config.getMaxrequests();


       if(second - lastWindowStartSeconds <= desiredWindow){
            int counter = window.getCounter();
            if(counter < maxRequests){
                window.setCounter(counter+1);
                return true;
            }
       }else{
           window.setWindowStart(now);
           window.setCounter(1);
           return true;

       }


       return false;
    }
}
