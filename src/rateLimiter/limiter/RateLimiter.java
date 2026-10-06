package rateLimiter.limiter;

public interface RateLimiter {
    boolean allowRequest(String userId);
}
