package com.zl.concurrency.example.concurrent;

import com.zl.concurrency.example.temp.AbstractCountDownLunchTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ConcurrentSkipListSet验证--->是TreeSet的并发实现
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 20:35
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class ConcurrentSkipListSetExample extends AbstractCountDownLunchTemp{
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        ConcurrentSkipListSetExample copyOnWriteExample = new ConcurrentSkipListSetExample();
        try {
            copyOnWriteExample.execute(clientTotal,threadTotal);
            log.info("集合大小是：{}",set.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void definedMethods(int param) {
        set.add(param);
    }
}
