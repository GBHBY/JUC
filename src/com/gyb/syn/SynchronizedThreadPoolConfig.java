package com.gyb.syn;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @author GB
 * @date 2023/8/1 1:55
 */
public class SynchronizedThreadPoolConfig {

    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int MAX_SIZE = CORE_SIZE + 1;

    private static final long KEEP_ALIVE_TIME = 60;

    private static final int TASK_QUEUE_SIZE = 5000;

    private static volatile ThreadPoolExecutor executor;


    public ThreadPoolExecutor getThreadPool() {
        if(Objects.isNull(executor)){
            synchronized (this) {
                if(Objects.isNull(executor)){
                    BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(TASK_QUEUE_SIZE);
                    executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                            taskQueue, new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executor;
    }

}
