package org.zhangyc.test.completableFuture;

import java.util.concurrent.CompletableFuture;

public class Test {
    public static void main(String[] args) {
        try {
            String value = CompletableFuture.supplyAsync(() -> "Hello")
                    .thenApply(s -> s + " World")
                    .thenApply(String::toUpperCase).get();
            System.out.println(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
