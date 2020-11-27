package com.gyb.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author gb
 * @version 1.0
 * description:CountDownLatch简单介绍
 * @date 2020/11/16 21:19
 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CountDownLatch cdl = new CountDownLatch(6);
        FutureTask task = new FutureTask(new MyThread4());
        new Thread(task, "A").start();
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "同学");
                /**创建CountDownLatch的时候设定的值是6，所以会6会递减，走一个线程6就会减一，直到减完**/
                cdl.countDown();
            }, String.valueOf(i)).start();
            /**在6没有减完的时候，这个方法会让其他线程等待**/
            cdl.await();
            System.out.println(task.get());

        }
    }

}

class MyThread4 implements Callable {

    @Override
    public Object call() throws Exception {
        return "最终执行";
    }
}