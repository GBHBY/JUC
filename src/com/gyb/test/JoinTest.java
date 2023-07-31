package com.gyb.test;

/**
 * Description:
 *
 * @author GB
 * @date 2022/8/12 9:18
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable x = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "=========");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(x);
        thread.start();
        thread.join();

        System.out.println("890890");


    }

}
