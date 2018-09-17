package com.zl.concurrency.example.concurrent;

import com.zl.concurrency.example.temp.AbstractCountDownLunchTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayiList验证--->是arrayList的并发操作类
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 20:35
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class CopyOnWriteArrayListExample extends AbstractCountDownLunchTemp{
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        CopyOnWriteArrayListExample copyOnWriteExample = new CopyOnWriteArrayListExample();
        try {
            copyOnWriteExample.execute(clientTotal,threadTotal);
            log.info("集合大小是：{}",list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void definedMethods(int param) {
        list.add(param);
    }
}
