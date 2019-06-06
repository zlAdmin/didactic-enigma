package com.zl.concurrency.example.temp;

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
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public abstract class AbstractCountDownLunchTemp {
    protected int clientTotal ;
    protected int threadTotal ;
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void execute(int clientTotal, int threadTotal) throws Exception {
        this.clientTotal = clientTotal;
        this.threadTotal = threadTotal;
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i ++ ) {
            final int param = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    definedMethods(param);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception"+e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("结束:{}","执行完毕！");
    }
    /**
     * @Author zhanglei
     * @Description  自定义方法
     * @Date 9:36 2018/9/17
     * @Param  传入int值
     * @return
     **/
    public abstract void definedMethods(final int param);
}
