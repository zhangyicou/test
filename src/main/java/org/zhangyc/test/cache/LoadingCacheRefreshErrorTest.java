package org.zhangyc.test.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class LoadingCacheRefreshErrorTest {
    public LoadingCache<String, String> loadingCache;

    public LoadingCacheRefreshErrorTest() {
        loadingCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .refreshAfterWrite(5, TimeUnit.SECONDS).build(
                new CacheLoader<String, String>() {

                    @Override
                    public String load(String key) throws Exception {
                        return refresh();
                    }

                    @Override
                    public String reload(String key, String oldValue) throws Exception {
                        try {
                            return refresh();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return oldValue;
                    }
                });
    }

    public static void main(String[] args) {
        LoadingCacheRefreshErrorTest test = new LoadingCacheRefreshErrorTest();
        for (int i = 1; i <  100; i++) {
            try {
                System.out.println("Before Get Cache");
                System.out.println("" + test.loadingCache.get("Country"));
                System.out.println("After Get Cache");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String refresh() throws Exception{

        return "China."+System.currentTimeMillis();
    }
}
