package com.gyb.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2020/11/16 18:20
 */

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new MyThread3());
        new Thread(futureTask,"A").start();
        for (int i = 0; i < 5; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
            }, "同学" + i).start();
        }
        System.out.println(futureTask.get());

    }
}


class MyThread3 implements Callable {


    @Override
    public Object call() throws Exception {

        return "锁门";
    }
}
