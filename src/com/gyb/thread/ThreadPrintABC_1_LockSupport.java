package com.gyb.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * Description: 通过lockSupport实现顺序打印
 *
 * @author GB
 * @date 2023/7/20 16:31
 */
public class ThreadPrintABC_1_LockSupport {

    private static Thread threadA, threadB, threadC;

    public static void main(String[] args) {
        threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 打印当前线程名称
                System.out.print(Thread.currentThread().getName());
                // 唤醒下一个线程
                LockSupport.unpark(threadB);
                // 当前线程阻塞
                LockSupport.park();
            }
        }, "A");
        threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 先阻塞等待被唤醒
                LockSupport.park();
                System.out.print(Thread.currentThread().getName());
                // 唤醒下一个线程
                LockSupport.unpark(threadC);
            }
        }, "B");
        threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 先阻塞等待被唤醒
                LockSupport.park();
                System.out.print(Thread.currentThread().getName());
                // 唤醒下一个线程
                LockSupport.unpark(threadA);
            }
        }, "C");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
