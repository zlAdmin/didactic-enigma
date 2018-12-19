package com.zl.concurrency.example.efficientcache;

import com.zl.concurrency.example.ThreadPoolResource.ThreadPoolInstance;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-19 21:47
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class TestMain extends Memoizer4<String, BigInteger>{
    private static final ExecutorService executorService = ThreadPoolInstance.getThreadPollInstance(5, 200, 0L);
    private static String[] arr = new String[]{"1","1","111","10","10","1","2","3"};
    public TestMain(ExpensiveFunction c) {
        super(c);
    }

    public static void main(String[] args) throws InterruptedException {
        ExpensiveFunction expensiveFunction = new ExpensiveFunction();
        TestMain tm = new TestMain(expensiveFunction);

        long startTime = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(arr.length);
        log.info("计算开始....");
        for (int i = 0; i < arr.length; i++) {
            final int j = i;
            executorService.execute(()->{
                try {
                    log.info("值是：{}，结果是：{}",arr[j ],tm.compute(arr[j ]));
                } catch (InterruptedException e) {
                    log.error("exception"+e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        log.info("全部计算结束....");
        long endTime = System.currentTimeMillis();
        log.info("计算次数为：{}",expensiveFunction.getCalculationTimes());
        log.info("计算时间是：{}",endTime - startTime);


    }
}
