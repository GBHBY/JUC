package com.gyb.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author GB
 * @desc
 * @date 2023/10/8 23:51
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        List<CompletableFuture<?>> completableFutures = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            completableFutures.add(CompletableFuture.runAsync(() -> {
                        System.out.println(finalI);
                    })
            );
        }
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
        System.out.println("123");
    }


}
