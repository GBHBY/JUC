package com.gyb.block;

/**
 * @author gb
 * @version 1.0
 * description:阻塞线程的几种方式
 * @date 2021/7/14 0:02
 */

public class BlockYield extends Thread {
    public BlockYield() {
        super.setName("BlockYield线程");
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getId() + "--" + Thread.currentThread().getName() + "是我在执行" + "--" + i);
            if (i == 10) {
                /**这个只是对线程调度器的一种建议，建议去执行相同优先级的线程，并不是每次都能换成别的线程去执行，**/
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        BlockYield blockYield = new BlockYield();
        Thread t = new Thread(blockYield);
        Thread t1 = new Thread(blockYield);
        Thread t2 = new Thread(blockYield);
        t.start();
        t1.start();
        t2.start();

    }


}
