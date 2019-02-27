package org.zhangyc.test.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadingCacheRefreshTest {
    public LoadingCache<String, String> loadingCache;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public LoadingCacheRefreshTest() {
        loadingCache = Caffeine.newBuilder().refreshAfterWrite(5, TimeUnit.SECONDS).build(
                new CacheLoader<String, String>() {
                    private AtomicInteger count = new AtomicInteger(1);
                    private AtomicInteger reloadCount = new AtomicInteger(1);

                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("Load Value " + count.get() + " time(s)");

                        try{
                            TimeUnit.SECONDS.sleep(1);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        count.incrementAndGet();
                        return "China";
                    }

                    @Override
                    public String reload(String key, String oldValue) throws Exception {
                        System.out.println("..........................Reload for " + reloadCount.get() + " time(s)");
                        ;
//                        ListenableFutureTask<String> futureTask = ListenableFutureTask.create(() -> {
//                            for (int i = 0; i < 10; i++) {
//                                System.out.println("Reload Value for " + i + " second(s)");
//                                Thread.currentThread().sleep(1000);
//                            }
                        int count = reloadCount.incrementAndGet();
//                            return "China" + count;
//                        });
//                        executorService.execute(futureTask);

                        try{
                            TimeUnit.SECONDS.sleep(1);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        Random random = new Random();
                        String value = String.valueOf(random.nextInt());
                        System.out.println("..........................value="+value);
                        return value;
                    }
                });
    }

    public static void main(String[] args) {
        LoadingCacheRefreshTest test = new LoadingCacheRefreshTest();
        Runnable runnable1 = () -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    System.out.println("Runnable1 Before Get Cache");
                    System.out.println("Runnable1 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable1 After Get Cache");
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2 = () -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    System.out.println("Runnable2 Before Get Cache");
                    System.out.println("Runnable2 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable2 After Get Cache");
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable runnable3 = () -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    System.out.println("Runnable3 Before Get Cache");
                    System.out.println("Runnable3 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable3 After Get Cache");
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable runnable4 = () -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    System.out.println("Runnable4 Before Get Cache");
                    System.out.println("Runnable4 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable4 After Get Cache");
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable runnable5 = () -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    System.out.println("Runnable5 Before Get Cache");
                    System.out.println("Runnable5 " + test.loadingCache.get("Country"));
                    System.out.println("Runnable5 After Get Cache");
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        Thread thread4 = new Thread(runnable4);
        Thread thread5 = new Thread(runnable5);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
