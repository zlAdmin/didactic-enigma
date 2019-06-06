package com.zl.demo.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * li
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-11-21 15:18
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class ThreadLocalTest {

    private static final ThreadLocal<AtomicBoolean> partsIsSatisfying1 = new ThreadLocal<>() {};

    private static final ThreadLocal<String> ss = new ThreadLocal<>();

    private static final ThreadLocal<AtomicBoolean> partsIsSatisfying = ThreadLocal.withInitial(() -> new AtomicBoolean(false));

    public static void main(String[] args) {
        ss.set("11223");
        System.out.println(ss.get());
        ss.set("11223555");
        System.out.println(ss.get());
        /*partsIsSatisfying.get().compareAndSet(false, true);
        System.out.println(partsIsSatisfying.get().get());
        partsIsSatisfying.get().compareAndSet(true, false);
        System.out.println(partsIsSatisfying.get().get());
*/
    }

    private static void stetThread(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            if (1 == i) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                    partsIsSatisfying.get().compareAndSet(false, true);
                    System.out.println(partsIsSatisfying.get().get());
                });
            } else if (2 == i) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(partsIsSatisfying.get().get());
                });
            } else {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(partsIsSatisfying.get().get());
                });
            }
        }
    }

}
