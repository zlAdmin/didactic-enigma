package com.zl.concurrency.example.object.waitnotify;

import com.zl.concurrency.example.ThreadPoolResource.ThreadPoolInstance;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 生产者消费者主调用函数
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-06 17:46
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class Main {
    private static final ExecutorService executorService = ThreadPoolInstance.getThreadPollInstance(5, 200, 0L);

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>(100);
        Producers producers = new Producers(queue, 10);
        Consumers consumers = new Consumers(queue, 20);
        executorService.execute(producers);
        executorService.execute(consumers);
    }
}
