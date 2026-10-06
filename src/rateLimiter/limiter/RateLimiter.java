package rateLimiter.limiter;

import rateLimiter.models.User;

public interface RateLimiter {
    boolean allowRequest(User user);
}
