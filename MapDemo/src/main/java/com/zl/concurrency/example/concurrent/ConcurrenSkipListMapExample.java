package com.zl.concurrency.example.concurrent;

import com.zl.concurrency.annoations.ThreadSafe;
import com.zl.concurrency.example.temp.AbstractCountDownLunchTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap验证--->是TreeMap的并发实现
 * 使用场景：key值是有序的，支持更高并发场景，但是性能没有ConcurrentHashMap好
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 20:35
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
@ThreadSafe
public class ConcurrenSkipListMapExample extends AbstractCountDownLunchTemp{
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;

    private static Map<Integer,Integer> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) {
        ConcurrenSkipListMapExample copyOnWriteExample = new ConcurrenSkipListMapExample();
        try {
            copyOnWriteExample.execute(clientTotal,threadTotal);
            log.info("集合大小是：{}",map.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void definedMethods(int param) {
        map.put(param,param);
    }
}
