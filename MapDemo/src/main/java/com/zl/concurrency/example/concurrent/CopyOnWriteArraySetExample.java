package com.zl.concurrency.example.concurrent;

import com.zl.concurrency.example.temp.AbstractCountDownLunchTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet验证--->是HashSet的并发实现
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 20:35
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class CopyOnWriteArraySetExample extends AbstractCountDownLunchTemp{
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;

    private static Set<Integer> set = new CopyOnWriteArraySet<>();

    public static void main(String[] args) {
        CopyOnWriteArraySetExample copyOnWriteExample = new CopyOnWriteArraySetExample();
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
