package com.gyb.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gb
 * @version 1.0
 * description:
 * @date 2021/7/15 21:26
 */

public class Atomic {
    AtomicInteger  atomicInteger =  new AtomicInteger();
    ReentrantLock lock  =new ReentrantLock(true);
}
