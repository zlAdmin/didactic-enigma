package com.zl.concurrency.example.object.waitnotify;

import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

/**
 * 基于Object中的notify和wait实现生产者消费者模式
 * 消费者，每隔两秒消费一条数据
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-06 17:28
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class Consumers implements Runnable {
    private BlockingQueue<String> queue;
    /**
     * 工作效率
     */
    private long millis;

    public Consumers(BlockingQueue<String> queue, long millis) {
        this.queue = queue;
        this.millis = millis;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(millis);
                String str = queue.take();
                if (StringUtils.isNullOrEmpty(str)) {
                    synchronized (queue) {
                        queue.wait();
                    }
                } else {
                    synchronized (queue) {
                        queue.remove(0);
                        queue.notifyAll();
                        log.info("消费者消费消息:{}", str);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
