package com.zl.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-23 21:38
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class FutureTaskTest {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "DONE";
            }
        });
        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info(result);

    }
}
