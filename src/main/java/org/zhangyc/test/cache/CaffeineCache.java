package org.zhangyc.test.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by oker on 2019/1/14.
 */
public class CaffeineCache {
    private Random random = new Random();
    private final AsyncLoadingCache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(10_000_000)
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .buildAsync(this::load);



    public String getString(String key){
        try{
            return cache.get(key, this::load).get();
        } catch (Exception e) {
            System.out.println("Get user config caffeine error, key:" + key);
        }

        return null;
    }

    public String load(String key){
        System.out.println("---load-------------");
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        CaffeineCache caffeineCache = new CaffeineCache();
        for(int i = 0 ; i < 1000; i++) {
            System.out.println(caffeineCache.getString("123"));
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
