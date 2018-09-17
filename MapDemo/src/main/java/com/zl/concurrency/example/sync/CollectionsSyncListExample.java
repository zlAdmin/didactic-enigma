package com.zl.concurrency.example.sync;

import com.zl.concurrency.example.temp.AbstractCountDownLunchTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections内部的线程安全类测试
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 19:41
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class CollectionsSyncListExample extends AbstractCountDownLunchTemp {
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;

    public static void main(String[] args) {
        CollectionsSyncListExample collectionsSyncListExample = new CollectionsSyncListExample();
        try {
            collectionsSyncListExample.execute(clientTotal,threadTotal);
            log.info("集合大小是：{}",list.size());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("异常");
        }

    }

    @Override
    public void definedMethods(int param) {
        list.add(param);
    }
}
