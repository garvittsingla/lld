package rateLimiter;

import rateLimiter.enums.UserTier;
import rateLimiter.limiter.FixedWindowCounterAlgorithm;
import rateLimiter.limiter.RateLimiter;
import rateLimiter.limiter.TokenBucketAlgorithm;
import rateLimiter.models.RateLimitConfig;
import rateLimiter.models.User;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        RateLimitConfig config =
                new RateLimitConfig(5, 10);

        RateLimiter rateLimiter =
                new FixedWindowCounterAlgorithm(config);

        User user = new User("garvit", UserTier.FREE);

        for (int i = 1; i <= 20; i++) {

            boolean allowed = rateLimiter.allowRequest(user);

            System.out.println(
                    "Request " + i + ": " + allowed
            );

            if(i == 10) Thread.sleep(10000);

        }
    }
}