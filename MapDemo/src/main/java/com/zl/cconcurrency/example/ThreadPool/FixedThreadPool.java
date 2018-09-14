package com.zl.cconcurrency.example.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.cconcurrency.example.ThreadPool
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: zhanglie
 * @CreateDate: 2018/9/13 17:43
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/13 17:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Slf4j
public class FixedThreadPool {

    /** 请求总数**/
    private static  int clientTotal = 100;

    /** 同时并发执行的线程数 **/
    private static  int threadTotal = 5;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            atomicInteger.incrementAndGet();
            while (atomicInteger.get() > 4){
                Thread.sleep(5);
                log.info("--------------");
            }
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    consoleLog();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception" + e);
                }
                countDownLatch.countDown();
                atomicInteger.decrementAndGet();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
    }
    private static void consoleLog(){
        try {
            log.info("hello");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
