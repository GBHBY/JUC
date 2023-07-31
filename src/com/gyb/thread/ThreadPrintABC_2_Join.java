package com.gyb.thread;

/**
 * Description:
 *
 * @author GB
 * @date 2023/7/20 16:31
 */
public class ThreadPrintABC_2_Join {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            System.out.println("A");
        });

        Thread two = new Thread(() -> {
            System.out.println("B");
        });

        Thread three = new Thread(() -> {
            System.out.println("C");
        });

        one.start();
        one.join();

        two.start();
        two.join();

        three.start();
        three.join();

        // 顺序打印0-9
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                System.out.println(finalI);
            });
            thread.start();
            thread.join();
        }

    }


    public synchronized void test(){

    }
}
