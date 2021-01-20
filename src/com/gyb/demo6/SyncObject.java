package com.gyb.demo6;//: concurrency/SyncObject.java
// Synchronizing on another object.

import java.text.SimpleDateFormat;
import java.util.Date;

class DualSynch {
    private Object syncObject = new Object();

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()"+System.currentTimeMillis());
//            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()"+System.currentTimeMillis());
//                Thread.yield();
            }
        }
    }
}

/**
 * @author asus
 */
public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();

    }
} /* Output: (Sample)
g()
f()
g()
f()
g()
f()
g()
f()
g()
f()
*///:~
