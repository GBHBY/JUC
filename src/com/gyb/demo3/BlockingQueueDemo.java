package com.gyb.demo3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author gb
 * @version 1.0
 * description:阻塞队列
 * @date 2020/11/22 23:28
 */

public class BlockingQueueDemo {
    public static void make(int a) {
         a = a * 2;
    }

    public static void main(String[] args) {
        int age = 30;
        make(age);
        System.out.println(age);
    }

}
