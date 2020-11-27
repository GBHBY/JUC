package com.gyb.demo4;

import java.util.concurrent.*;

/**
 * @author gb
 * @version 1.0
 * description:线程池
 * @date 2020/11/27 15:53
 */

public class Demo1 {


    public static void main(String[] args) {
        /**创建含有五个线程的线程池，但是这样做并不够完美**/
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        /**
         * 一次办一个
         */
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        /**一个线程池中有n多个，可扩容可伸缩**/
        //ExecutorService executorService = Executors.newCachedThreadPool();
        /**最后一个是拒绝策略，这个策略是默认策略，当任务线程数超过了最大数量+阻塞队列的容量，就会报错
         * 也可以使用"new ThreadPoolExecutor.CallerRunsPolicy()",使用这个阻塞策略，即使超过了最大＋阻塞队列容量，也不会报错，它会将超出的线程会退给调用线程
         * 又或者使用DiscardPolicy，这个会放弃超出的线程
         *
         * 第二个参数是获取内核数量，最大的线程数可以  设置为机器的内核数量
         *
         *
         */
        ExecutorService executorService = new ThreadPoolExecutor(2, Runtime.getRuntime().availableProcessors(), 2L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    }

}
