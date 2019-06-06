package com.zl.concurrency.example.sync;

import com.zl.concurrency.annoations.ThreadSafe;
import com.zl.concurrency.example.temp.AbstractCountDownLunchTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Collections内部的线程安全类测试
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 19:41
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
@ThreadSafe
public class CollectionsSyncSetExample extends AbstractCountDownLunchTemp {
    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
    private final static int clientTotal = 5000;
    private final static int threadTotal = 200;

    public static void main(String[] args) {
        CollectionsSyncSetExample collectionsSyncListExample = new CollectionsSyncSetExample();
        try {
            collectionsSyncListExample.execute(clientTotal,threadTotal);
            log.info("集合大小是：{}",set.size());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("异常");
        }

    }

    @Override
    public void definedMethods(int param) {
        set.add(param);
    }
}
