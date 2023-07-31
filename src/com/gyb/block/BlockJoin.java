package com.gyb.block;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2021/7/14 0:18
 */

public class BlockJoin extends Thread {
    public BlockJoin() {
        super.setName("BlockJoin线程");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + "--" + Thread.currentThread().getName() + "我");
    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread2 = new Thread(() -> {
            System.out.println("你");

        });

        Thread thread = new Thread(() -> {
            System.out.println("爱");
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread1 = new Thread(() -> {
            System.out.println("我");
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        thread1.start();
        thread.start();
        thread2.start();


    }
}
