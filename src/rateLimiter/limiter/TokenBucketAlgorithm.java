package rateLimiter.limiter;

import rateLimiter.models.RateLimitConfig;
import rateLimiter.models.TokenBucketState;
import rateLimiter.models.User;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class TokenBucketAlgorithm implements RateLimiter {

    private final Map<User,TokenBucketState> buckets = new HashMap<>();
    private RateLimitConfig config;

    public TokenBucketAlgorithm(RateLimitConfig config){
        this.config = config;
    }

    @Override
    public boolean allowRequest(User user) {

        long now = System.currentTimeMillis();

        TokenBucketState state = buckets.computeIfAbsent(
               user,
                user1 -> new TokenBucketState(
                        config.getMaxrequests(),
                        now
                )
        );

        long elapsedTime = now - state.getLastRefillTime();

        double elapsedSeconds = elapsedTime / 1000.0;

        double refillRate = (double)config.getMaxrequests() / config.getWindowSeconds();

        double tokensToAdd = refillRate * elapsedSeconds;

        double newTokens = Math.min(
                config.getMaxrequests(),
                tokensToAdd
        );

        state.setTokens(newTokens);
        state.setLastRefillTime(now);

        if(state.getTokens() < 1){
            return false;
        }

        state.setTokens(state.getTokens() - 1);

        return true;
    }
}
