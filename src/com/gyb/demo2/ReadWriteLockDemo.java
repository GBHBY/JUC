package com.gyb.demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gb
 * @version 1.0
 * description:读写锁
 * @date 2020/11/17 18:02
 */

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCatch myCatch = new MyCatch();
        for (int i = 0; i < 5; i++) {
            final int tem = i;
            new Thread(()->{
                myCatch.put(tem+"",tem+"");
            },String.valueOf(i)).start();

        }
        for (int i = 0; i < 5; i++) {
            final int tem = i;
            new Thread(() -> {
                myCatch.get(tem + "");
            }, String.valueOf(i)).start();

        }
    }


}

class MyCatch {
    private volatile Map<String, Object> map = new HashMap<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " " + "写入数据");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " " + "写入完成" + key);
        readWriteLock.writeLock().unlock();


    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "  开始读数据");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object value = map.get(key);
        System.out.println(Thread.currentThread().getName() + "  读数据完成" + value);
        readWriteLock.readLock().unlock();
    }

}
