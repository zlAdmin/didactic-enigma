package com.zl.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Future和Callable接口测试使用
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-23 21:32
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class FutureAndCallableTest {
     static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do someThing in callable");
            Thread.sleep(5000);
            return "DONE";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info(result);

    }
}
