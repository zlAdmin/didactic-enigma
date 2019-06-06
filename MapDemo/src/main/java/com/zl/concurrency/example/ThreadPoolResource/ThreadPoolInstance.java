package com.zl.concurrency.example.ThreadPoolResource;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 系统线程池资源
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-10-29 16:22
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class ThreadPoolInstance {

    private static  final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("zl_pool-%d").build();

    private static ExecutorService POOL = null;

    public static ExecutorService getThreadPollInstance(Integer corePoolSize, Integer maximumPoolSize, Long keepAliveTime){
        if (POOL == null) {
            POOL = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                    keepAliveTime, TimeUnit.MICROSECONDS,
                    new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        }
        return POOL;
    }


    public static void main(String[] args) {
        ExecutorService pool = getThreadPollInstance(5, 200, 0L);
        pool.execute(()-> System.out.println("开始执行"));
        pool.shutdown();
    }
}
