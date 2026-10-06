package rateLimiter.models;

public class RateLimitConfig {
    private final int maxRequests;
    private final long windowSeconds;

    public RateLimitConfig(int maxRequests,long windowSeconds){
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
    }

    public int getMaxrequests() {return maxRequests;}

    public long getWindowSeconds() {return windowSeconds;}


}
