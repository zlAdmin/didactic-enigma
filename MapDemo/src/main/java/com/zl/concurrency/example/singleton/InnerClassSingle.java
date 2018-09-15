package com.zl.concurrency.example.singleton;

import com.zl.concurrency.annoations.Recommend;
import com.zl.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用内部类实现单例模式
 *(通过jdk内部内)
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 14:02
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@ThreadSafe
@Recommend
@Slf4j
public class InnerClassSingle {
    private InnerClassSingle() {
    }

    private static class InsideClass {
       public  final static InnerClassSingle instance = new InnerClassSingle();
    }

    public static InnerClassSingle getInstance(){
        return InsideClass.instance;
    }

    public static void main(String[] args) throws InterruptedException {
        int clientTotal = 10;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()-> {
                InnerClassSingle instance =InnerClassSingle.getInstance();
                log.info("实例化对象为：{}",instance.hashCode());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}
