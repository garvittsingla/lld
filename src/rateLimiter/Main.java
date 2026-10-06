package rateLimiter;

import rateLimiter.enums.UserTier;
import rateLimiter.limiter.RateLimiter;
import rateLimiter.limiter.TokenBucketAlgorithm;
import rateLimiter.models.RateLimitConfig;
import rateLimiter.models.User;

public class Main {

    public static void main(String[] args) {

        RateLimitConfig config =
                new RateLimitConfig(5, 10);

        RateLimiter rateLimiter =
                new TokenBucketAlgorithm(config);

        User user = new User("garvit", UserTier.FREE);

        for (int i = 1; i <= 10; i++) {

            boolean allowed = rateLimiter.allowRequest(user);

            System.out.println(
                    "Request " + i + ": " + allowed
            );

        }
    }
}