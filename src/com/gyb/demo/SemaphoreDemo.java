package com.gyb.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author gb
 * @version 1.0
 * description:
 * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制
 * @date 2020/11/16 22:19
 */

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    /**作用是用来减少参数permits，每次减一**/
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "同学来了");
                    System.out.println();
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "同学走了");
                    System.out.println();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    /**释放资源，将permits参数加一**/
                    semaphore.release();
                }
            }, String.valueOf(i)).start();

        }
    }
}
