package com.sbip.ch06.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Feb, 2024
 */

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS_COUNT= 3;

    private final LoadingCache<String, Integer> loginAttemptCache;

    public LoginAttemptService() {
        this.loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }

    public void loginSuccess(String username){
        loginAttemptCache.invalidate(username);
    }

    public void loginFailed(String username){
        int failedAttemptCounter;

        try {
            failedAttemptCounter = loginAttemptCache.get(username);
        }catch (ExecutionException e){
            failedAttemptCounter = 0;
        }
        failedAttemptCounter++;
        loginAttemptCache.put(username, failedAttemptCounter);
    }

    public boolean isBlocked(String username){
        try{
            return loginAttemptCache.get(username) >= MAX_ATTEMPTS_COUNT;
        }catch (ExecutionException e){
            return false;
        }
    }
}
