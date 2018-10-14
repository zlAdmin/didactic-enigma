package com.zl.concurrency.example.aqs.lock;

import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock测试类
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-14 18:45
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class ReentrantReadWriteLockExample {

    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    public Data get(String key){
        readLock.lock();
        try {
            return  map.get(key);
        } finally {
            readLock.unlock();
        }

    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return  map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
          return  map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    class Data {

    }

}
