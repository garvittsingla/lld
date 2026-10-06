package rateLimiter.limiter;

import rateLimiter.models.RateLimitConfig;
import rateLimiter.models.TokenBucketState;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketAlgorithm implements RateLimiter{

    private final RateLimitConfig config;
    private final Map<String, TokenBucketState> buckets =
            new HashMap<>();

    public TokenBucketAlgorithm(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();

        TokenBucketState state = buckets.computeIfAbsent(
                userId,
                id -> new TokenBucketState(
                        config.getMaxrequests(),
                        now
                )
        );

        long elapsedTime = now - state.getLastRefillTime();

        double elapsedSeconds = elapsedTime / 1000.0;

        double refillRate =
                (double) config.getMaxrequests()
                        / config.getWindowSeconds();

        double tokensToAdd = elapsedSeconds * refillRate;

        double newTokens = Math.min(
                config.getMaxrequests(),
                state.getTokens() + tokensToAdd
        );

        state.setTokens(newTokens);
        state.setLastRefillTime(now);

        if (state.getTokens() < 1) {
            return false;
        }

        state.setTokens(state.getTokens() - 1);

        return true;
    }
}
