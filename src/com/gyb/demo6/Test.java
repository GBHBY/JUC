package com.gyb.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gb
 * @version 1.0
 * description:
 * 疑问： static、volatile、对于一个属性的影响，如何设计库存
 * @date 2021/4/10 23:10
 */

public class Test extends Thread {
    private static int count = 0;

    public Test(String s) {
        super();

    }

    @Override
    public void run() {
//        increase();
    }

    public synchronized void increase() {
        for (int i = 0; i < 10000; i++) {
            count++;
//            if (count > 99000) {
            System.out.println(this.getName() + "--" + count);
//            }
        }


    }


    public static void main(String[] args) {

        List<Test> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Test("Thread" + i));
            threads.get(i).increase();
            threads.get(i).start();
        }
        try {

            TimeUnit.SECONDS.sleep(5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(Test.count);


    }


}
