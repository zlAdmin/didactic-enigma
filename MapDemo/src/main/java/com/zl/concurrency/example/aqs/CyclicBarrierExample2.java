package com.zl.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier测试类
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-14 13:15
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier barrier = new CyclicBarrier(5,()->{
        log.info("callback is running");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exectorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            exectorService.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        exectorService.shutdown();
    }
    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{},is ready",threadNum);
        barrier.await();
        log.info("{} continue");
    }

}
