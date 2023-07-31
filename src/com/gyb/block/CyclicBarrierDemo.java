package com.gyb.block;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author gb
 * @version 1.0
 * description:
 * parties:线程数量，
 * barrierAction:最后一个线程要做的事情。
 * 举个例子：
 * 一个cyclicBarrier中有10个线程，for循环中我创建了50个线程，每10个吃完饭，在24行都会等待，等到最后一个线程吃完，最后一个线程就会收拾碗筷
 * @date 2021/7/15 23:34
 */

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println(Thread.currentThread().getName() + "收拾碗筷"));
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "吃完了");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }


            }).start();
        }
    }
}
