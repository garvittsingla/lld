package rateLimiter.limiter;

public interface RateLimiter {
    boolean allowRequests(String userId);
}
