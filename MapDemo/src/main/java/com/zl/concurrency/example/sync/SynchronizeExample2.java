package com.zl.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizeExample2 {

    /** 修饰一个类 **/
    public static void test1(){
        synchronized (SynchronizeExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("info-1:{}",i);
            }
        }
    }
    /** 修饰一个静态方法 **/
    public static synchronized void test2(){
        for (int i = 0; i < 10; i++) {
            log.info("info-2:{}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizeExample2 synchronizeExample1 = new SynchronizeExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            SynchronizeExample2.test1();
        });

        executorService.execute(() -> {
            SynchronizeExample2.test1();
        });
        executorService.execute(() -> {
            SynchronizeExample2.test2();
        });
        executorService.execute(() -> {
            SynchronizeExample2.test2();
        });
    }

}
