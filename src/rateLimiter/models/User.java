package rateLimiter.models;

import rateLimiter.enums.UserTier;

public class User {
    private final String userId;
    private final UserTier tier;

    public User(String userId,UserTier tier){
        this.tier = tier;
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public UserTier getTier(){
        return tier;
    }

}
