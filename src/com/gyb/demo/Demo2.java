package com.gyb.demo;

import java.util.Collections;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2020/11/14 17:23
 */
public class Demo2 {
    public static void main(String[] args) {
       
        Product product = new Product();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    product.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    product.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    product.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    product.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "D").start();


    }

}

/**
 * create by: gb
 * description: 资源类
 * create time: 2020/11/14 17:23
 */
class Product {
    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + " " + number);
        /**释放资源将控制权交给其他的线程**/
        this.notify();

    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " " + number);
        /**释放资源将控制权交给其他的线程**/
        this.notify();
    }

}
