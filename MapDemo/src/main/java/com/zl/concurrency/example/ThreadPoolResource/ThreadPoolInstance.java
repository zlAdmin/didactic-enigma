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
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
public class ThreadPoolInstance {

    public static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("zl_pool-%d").build();


    public static ExecutorService getThreadPollInstance(){
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        return pool;
    }


    public static void main(String[] args) {
        ExecutorService pool = getThreadPollInstance();
        pool.execute(()-> System.out.println("开始执行"));
        pool.shutdown();
    }
}
