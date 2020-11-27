package com.gyb.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author gb
 * @version 1.0
 * description:实现callable和实现Runnable的区别
 * callable有返回值
 * callable抛异常
 * callable的方法是call，Runnable是run
 * *********************callable的作用：******************************
 *          对于某些方法，较为耗时，但需要这个方法的结果来完成某个任务，比如，做饭，切菜比较重要，但是为了完成做菜，可以让一些小工去做，大工来做
 *          其他的，这样提高了程序的并行度，也提高了程序的性能
 * @date 2020/11/16 0:25
 */

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask, "A").start();
        /**获取返回值**/
        System.out.println(futureTask.get());

    }
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}


class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread2启动了");
        return 213;
    }
}

