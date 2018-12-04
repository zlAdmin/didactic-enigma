package com.zl.concurrency.example.temp;

import com.zl.concurrency.example.ThreadPoolResource.ThreadPoolInstance;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * CountDownLunch公用模板
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-14 16:59
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public abstract class AbstractCountDownLunchComm {
    protected int clientTotal ;
    protected int threadTotal ;
    private static final ExecutorService executorService = ThreadPoolInstance.getThreadPollInstance(5, 200, 0L);

    public void execute(int clientTotal, int threadTotal) throws Exception {
        this.clientTotal = clientTotal;
        this.threadTotal = threadTotal;
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i ++ ) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    definedMethods();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception"+e);
                }
                log.info("单执行完毕{}",Thread.currentThread().getId());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("结束:{}","执行完毕！");
    }
    /** 自定义方法 **/
    public abstract void definedMethods() throws InterruptedException;
}
