package com.gyb.test;

import java.util.concurrent.TimeUnit;

/**
 * @author gb
 * @version 1.0
 * description: 两个线程分别输出1-9 和 a-z 并且组合输出
 * @date 2021/7/20 13:31
 */

public class Test1 {
    static int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    static String[] str = new String[26];
    static char demo = 'A';

    public static void main(String[] args) {
        str[0] = (String.valueOf(demo));
        for (int i = 1; i <= 25; i++) {
            str[i] = (String.valueOf(++demo));
        }
        Object lock = new Object();
        Object lock2 = new Object();


        Thread thread = new Thread(() -> {
            synchronized (lock){
                for (int i = 0; i < 26; i++) {
                    if(i<=8){
                        System.out.print(num[i]);
                    }else if(i<=17){
                        System.out.print(num[i-9]);
                    }else {
                        System.out.print(num[i-18]);
                    }
                    try {
                        System.out.println("++");
                        lock.wait();
                        System.out.println("+");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("==");
                    lock.notify();
                    System.out.println("=");
                }

            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                for (int i = 0; i < str.length; i++) {
                    System.out.println(str[i]);
                    System.out.println("--");
                    lock.notify();
                    System.out.println("-");
                    try {
                        System.out.println("-+");
                        lock.wait();
                        System.out.println("-=");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        thread2.start();


    }
}
