package rateLimiter.models;

public class TokenBucketState {
    private double tokens;
    private long lastRefillTime;

    public TokenBucketState(double tokens, long lastRefillTime) {
        this.tokens = tokens;
        this.lastRefillTime = lastRefillTime;
    }

    public double getTokens() {
        return tokens;
    }

    public void setTokens(double tokens) {
        this.tokens = tokens;
    }

    public long getLastRefillTime() {
        return lastRefillTime;
    }

    public void setLastRefillTime(long lastRefillTime) {
        this.lastRefillTime = lastRefillTime;
    }
}
