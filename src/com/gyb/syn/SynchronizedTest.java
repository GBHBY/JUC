package com.gyb.syn;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 *
 * @author GB
 * @date 2023/8/1 1:53
 */
public class SynchronizedTest {

    private String test = "123";
    private String test2 = "1234";


    public synchronized void test1(String name) {
        for (int i = 0; i < 100; i++) {
            System.out.println(name);
        }
    }

    public synchronized void test2(String name) {
        for (int i = 0; i < 100; i++) {
            System.out.println(name);
        }
    }

    public static synchronized void test1Static(String name){
        for (int i = 0; i < 100; i++) {
            System.out.println(name);
        }
    }

    public static synchronized void test1Static2(String name){
        for (int i = 0; i < 100; i++) {
            System.out.println(name);
        }
    }

    public void testBlock1(String name){
        synchronized (test){
            for (int i = 0; i < 1000; i++) {
                System.out.println(name + "   " + i);
            }
        }
    }

    public void testBlock2(String name) {
        synchronized (test2){
//        synchronized (test) {
            for (int i = 0; i < 1000; i++) {
                System.out.println(name + "   " + i);
            }
        }
    }

    public void testStaticBlock3(String name){
        synchronized (SynchronizedTest.class){
            for (int i = 0; i < 100; i++) {
                System.out.println(name);
            }
        }
    }

    public void testStaticBlock4(String name){
        synchronized (SynchronizedTest.class){
            for (int i = 0; i < 100; i++) {
                System.out.println(name);
            }
        }
    }

    public void testBlock5(String name){
        synchronized (this){
            for (int i = 0; i < 100; i++) {
                System.out.println(name);
            }
        }
    }

    public void testBlock6(String name){
        synchronized (this){
            for (int i = 0; i < 100; i++) {
                System.out.println(name);
            }
        }
    }


    public static void main(String[] args) {
        SynchronizedThreadPoolConfig synchronizedThreadPoolConfig = new SynchronizedThreadPoolConfig();
        ThreadPoolExecutor threadPool = synchronizedThreadPoolConfig.getThreadPool();

        // --------------------- 测试synchronized修饰在普通方法上 ---------------------

        // 可以通过把test1()方法的synchronized修饰符去掉，就会发现， ‘第一个’ ‘第二个’ 会随机的去打印
        // synchronized标志锁住的是 synchronizedTest ，也就是锁住的是当前对象
        SynchronizedTest synchronizedTest = new SynchronizedTest();

        threadPool.execute(()->{
            synchronizedTest.test1("第一个");
        });

        threadPool.execute(()->{
            synchronizedTest.test2("第二个");
        });
        // --------------------- 测试synchronized修饰在普通方法上 ---------------------





        // --------------------- 测试synchronized修饰在静态方法上 ---------------------

        // 修饰在静态方法上，锁住的是这个类，与创建的对象无关
        threadPool.execute(()->{
            test1Static("静态第一个");
        });

        threadPool.execute(()->{
            test1Static2("静态第二个");
        });
        // --------------------- 测试synchronized修饰在静态方法上 ---------------------


        // --------------------- 测试synchronized修饰在方法块上，锁住Object ---------------------
        SynchronizedTest synchronizedTest1 = new SynchronizedTest();
        // synchronized锁住的是test或者test2对象，如果把testBlock2方法的51行注释掉就会发现，两个打印会顺序执行，否则会交替执行，
        // 交替执行是因为锁住的是不同的对象


        threadPool.execute(()->{
            synchronizedTest1.testBlock1("第一个");
        });

        threadPool.execute(()->{
            synchronizedTest1.testBlock2("第二个");
        });

        // --------------------- 测试synchronized修饰在方法块上，锁住Object ---------------------














    }


}
