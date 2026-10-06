package rateLimiter;

import rateLimiter.limiter.RateLimiter;
import rateLimiter.limiter.TokenBucketAlgorithm;
import rateLimiter.models.RateLimitConfig;

public class Main {

    public static void main(String[] args) {

        RateLimitConfig config =
                new RateLimitConfig(5, 10);

        RateLimiter rateLimiter =
                new TokenBucketAlgorithm(config);

        String userId = "user1";

        for (int i = 1; i <= 10; i++) {

            boolean allowed =
                    rateLimiter.allowRequest(userId);

            System.out.println(
                    "Request " + i + ": " + allowed
            );

        }
    }
}