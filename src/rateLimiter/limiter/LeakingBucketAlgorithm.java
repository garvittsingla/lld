package rateLimiter.limiter;

import rateLimiter.models.LeakingBucketState;
import rateLimiter.models.RateLimitConfig;
import rateLimiter.models.User;

import java.util.HashMap;
import java.util.Map;

public class LeakingBucketAlgorithm implements RateLimiter{
    private final RateLimitConfig config;
    private final Map<User, LeakingBucketState> buckets = new HashMap<>();

    public LeakingBucketAlgorithm(RateLimitConfig config){
        this.config = config;
    }

    @Override
    public boolean allowRequest(User user) {
        long now = System.currentTimeMillis();

        LeakingBucketState state = buckets.computeIfAbsent(
                user,
                user1-> new LeakingBucketState(
                        now
                )
        );

        long elapsedTime = now - state.getLastLeakTime();

        double elapsedSeconds =
                elapsedTime / 1000.0;

        double leakRate =
                (double) config.getMaxrequests()
                        / config.getWindowSeconds();

        long requestsToLeak =
                (long) (elapsedSeconds * leakRate);

        for (long i = 0; i < requestsToLeak; i++) {

            if (state.getRequests().isEmpty()) {
                break;
            }

            state.getRequests().poll();
        }

        state.setLastLeakTime(now);

        if (state.getRequests().size()
                >= config.getMaxrequests()) {

            return false;
        }

        state.getRequests().offer(now);

        return true;

    }
}
