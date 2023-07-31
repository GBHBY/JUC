package com.gyb.test;

import com.sun.activation.registries.LogSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author gb
 * @version 1.0
 * description: 两个线程分别输出1-9 和 a-z 并且组合输出
 * @date 2021/7/20 13:31
 */

public class Test2 {
    static int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    static String[] str = new String[26];
    static char demo = 'A';

    public static void main(String[] args) {
        str[0] = (String.valueOf(demo));
        for (int i = 1; i <= 25; i++) {
            str[i] = (String.valueOf(++demo));
        }

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                if (i <= 8) {
                    System.out.print(num[i]);
                } else if (i <= 17) {
                    System.out.print(num[i - 9]);
                } else {
                    System.out.print(num[i - 18]);
                }
                LockSupport.park();
            }
        });
        thread.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < str.length; i++) {
                System.out.println(str[i]);
                LockSupport.unpark(thread);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        thread2.start();


    }
}
