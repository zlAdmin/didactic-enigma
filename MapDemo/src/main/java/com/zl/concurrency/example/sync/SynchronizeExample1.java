package com.zl.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizeExample1 {

    /** 修饰一个代码块 **/
    public void test1(){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("info-1:{}",i);
            }
        }
    }
    /**修饰一个方法**/
    public synchronized void test2(){
        for (int i = 0; i < 10; i++) {
            log.info("info-2:{}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizeExample1 synchronizeExample1 = new SynchronizeExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizeExample1.test1();
        });

        executorService.execute(() -> {
            synchronizeExample1.test1();
        });
        executorService.execute(() -> {
            synchronizeExample1.test2();
        });
        executorService.execute(() -> {
            synchronizeExample1.test2();
        });
    }

}
