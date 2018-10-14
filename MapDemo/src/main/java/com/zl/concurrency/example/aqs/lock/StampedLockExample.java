package com.zl.concurrency.example.aqs.lock;

import com.zl.concurrency.example.count.ContThreadSafeExample;
import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock测试类
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-14 18:57
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class StampedLockExample extends AbstractCountDownLunchComm {
    /** 请求总数**/
    public static  int clientTotal = 5000;
    /** 同时并发执行的线程数 **/
    public static  int threadTotal = 200;

    public static  int count = 0;

    private final static StampedLock LOCK = new StampedLock();

    public static void main(String[] args) throws Exception {

        new ContThreadSafeExample().execute(clientTotal,threadTotal);
        log.info("count:{}",count);
    }


    @Override
    public synchronized void definedMethods() {
        long stamp = LOCK.writeLock();
        try {
            count++;
        } finally {
            LOCK.unlock(stamp);
        }
    }
}
