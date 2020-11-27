package com.gyb.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2020/11/12 0:19
 */
public class Demo1 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 1; i <= 35; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 35; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 35; i++) {
                ticket.sale();
            }
        }, "C").start();


    }

}


class Ticket {
    int number = 30;
    private Lock lock = new ReentrantLock();

    /**
     * create by: gb
     * description: 和synchronized 对比，synchronized是放在方法上，假设在操作一个表，那么会把这个表给锁起来，降低了并发性，但lock仅仅是所著某哥操作，提高了并发性
     * create time: 2020/11/14 1:20
     */
    public void sale() {


        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t 卖出第: " + (number--) + "\t 还剩下：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
