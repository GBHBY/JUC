package com.gyb.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author GB
 * @date 2023/7/20 16:31
 */
public class ThreadPrintABC_1_Lock {
    private static Lock lock = new ReentrantLock();
    private static int state = 0;

    static class MyThead extends Thread {
        private int flag;

        private String name;

        public MyThead(int flag, String name) {
            this.flag = flag;
            this.name = name;
        }

        @Override
        public void run() {
            // 这个方法是用来获取锁的，每个线程在执行的时候都会先执行lock方法，如果能够获取到锁（说明没有其他线程获取到锁），就向下执行。
            // 否则如果获取不到，就会被阻塞住
            lock.lock();
            try {
                if (state == flag) {
                    state++;
                    System.out.println("========== " + name + " ==========" + flag);
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            MyThead myThead = new MyThead(i, String.valueOf(i));
            myThead.start();
            myThead.join();
        }
    }

}
