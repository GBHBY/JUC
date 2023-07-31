package com.gyb.threadpool;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理
 *
 * @author wenhuagui
 * @version 1.0
 * @date 2021-06-23
 */
@Slf4j
public class ThreadManageUtil {
    volatile private static ThreadPoolExecutor EXECUTOR = null;

    /**
     * 根据cpu 数量动态配置核心线程数和最大线程数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数
     */
    private static final int CORE_PO0L_SIZE = CPU_COUNT + 1;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 2 * CPU_COUNT + 1;
    /**
     * 线程队列
     */
    private static final int MAX_LIMIT_JOB_SIZE = 1000;
    /**
     * 非核心线程存活时间1s
     */
    private static final int KEEP_ALIVE = 1;

    public static ThreadPoolExecutor getThreadPool() {
        if (null == EXECUTOR) {
            synchronized (ThreadManageUtil.class) {
                if (null == EXECUTOR) {
                    EXECUTOR = new ThreadPoolExecutor(
                            CORE_PO0L_SIZE,
                            MAX_POOL_SIZE,
                            KEEP_ALIVE,
                            TimeUnit.MICROSECONDS,
                            new LinkedBlockingDeque<>(MAX_LIMIT_JOB_SIZE),
                            Executors.defaultThreadFactory(),
                            new ThreadPoolExecutor.AbortPolicy() {
                                @Override
                                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                                    log.error("队列已满，且当前线程数以超过最大线程数");
                                    super.rejectedExecution(r, e);
                                }
                            }
                    );
                }
            }
        }
        return EXECUTOR;
    }

    public static void execute(Runnable runable) {
        getThreadPool().execute(runable);

    }


}
