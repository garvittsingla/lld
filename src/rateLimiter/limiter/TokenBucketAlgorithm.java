package rateLimiter.limiter;

import rateLimiter.models.RateLimitConfig;
import rateLimiter.models.TokenBucketState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketAlgorithm implements RateLimiter{

    private final RateLimitConfig config;
    private final Map<String, TokenBucketState> buckets =
            new ConcurrentHashMap<>();

    public TokenBucketAlgorithm(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean allowRequest(String userId) {
        return false;
    }
}
