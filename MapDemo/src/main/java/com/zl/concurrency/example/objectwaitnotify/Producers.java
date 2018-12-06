package com.zl.concurrency.example.objectwaitnotify;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于Object的notify和wait实现生产者消费者模式
 *（生产者）想list中不断放入记录
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-06 17:16
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class Producers implements Runnable{
    private BlockingQueue<String> queue;
    private long millis;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public Producers(BlockingQueue<String> queue, long millis) {
        this.queue = queue;
        this.millis = millis;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(millis);
                synchronized (queue) {
                    if (queue.size() >= 100){
                        queue.wait();
                    }
                    int news =atomicInteger.incrementAndGet();
                    if (queue.offer("生产者生产消息" + news) ){
                        log.info("新增消息:{}", news);
                        queue.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
