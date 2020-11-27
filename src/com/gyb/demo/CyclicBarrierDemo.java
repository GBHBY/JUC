package com.gyb.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2020/11/16 21:57
 */

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println("结束");
        });

        for (int i = 0; i < 6; i++) {
            /**必须使用final，否则在lamda中会报错**/
            final int tem = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "" + tem);
                try {
                    /**直到七个线程走完，才会执行创建cyclicBarrier时第二个参数的线程**/
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }
}
