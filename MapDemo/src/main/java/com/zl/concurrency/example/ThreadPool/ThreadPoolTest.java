package com.zl.concurrency.example.ThreadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-24 22:48
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    log.info("hello");
                }
            });
        }
        executorService.shutdown();
        /*createThreadPool();*/
    }

    /**
     * 手动定义线程池
     */
    public static void createThreadPool(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        pool.execute(()-> {
            log.info(Thread.currentThread().getName());
        });
        pool.shutdown();
    }
}
