package com.gyb.block;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GB
 * @desc
 * @date 2023/10/9 00:28
 */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount); // 创建计数器对象，初始值为线程数

        ExecutorService executorService = Executors.newFixedThreadPool(10); // 创建线程池
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown(); // 当前线程执行完毕后，计数器减1
                System.out.println(Thread.currentThread().getName() + "123");
            });
        }

        countDownLatch.await(); // 等待所有线程执行完毕
        executorService.shutdown(); // 关闭线程池

        System.out.println("完成");
    }
}