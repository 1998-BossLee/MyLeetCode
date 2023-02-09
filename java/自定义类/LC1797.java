package 自定义类;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1797 {
}

class AuthenticationManager {

    int timeToLive;

    Map<String, Integer> expireMap;

    public AuthenticationManager(int timeToLive) {
        expireMap = new HashMap<>();
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        int expireTime = currentTime + this.timeToLive;
        expireMap.put(tokenId, expireTime);
    }

    public void renew(String tokenId, int currentTime) {
        Integer expireTime = expireMap.get(tokenId);
        if (expireTime != null) {
            if (expireTime <= currentTime) {
                expireMap.remove(tokenId);
            } else {
                expireMap.put(tokenId, currentTime + this.timeToLive);
            }
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int sum = 0;
        List<String> expireKeys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : expireMap.entrySet()) {
            if (entry.getValue() > currentTime) {
                sum++;
            } else {
                expireKeys.add(entry.getKey());
            }
        }
        for (String expireKey : expireKeys) {
            expireMap.remove(expireKey);
        }
        return sum;
    }
}
