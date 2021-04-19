package com.loca.mallstu.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangHeng
 * @date  2021-04-15 17:52
 */
public class AsyncThreadPoolUtil {
    public static final ThreadPoolExecutor BATCH_APPROVE_POOL =
            new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10240),
                    (r) -> new Thread(r));
}
