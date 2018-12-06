package com.zl.concurrency.example.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阿里的TransmittableThreadLocal在使用线程池等会池化复用线程的组件情况下传递ThreadLocal
 *
 * 解决的问题：除了拥有InheritableThreadLocal的功能外，解决了InheritableThreadLocal线程池引发的问题
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-06 10:24
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class TransmittableThreadLocalExample2 extends AbstractCountDownLunchComm {
    private static final TransmittableThreadLocal THREAD_LOCAL = new TransmittableThreadLocal();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static String [] str =  new String[]{"java","hadoop","MapReduce","MapReduce","MapReduce","yarn","hdfs","hadoop","MapReduce"};

    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16);
        map.notify();
        THREAD_LOCAL.set(map);
        TransmittableThreadLocalExample2 transmittableThreadLocalExample = new TransmittableThreadLocalExample2();
        transmittableThreadLocalExample.notify();
        transmittableThreadLocalExample.execute(8, 4);
        log.info("父线程获取TEST_IN_THREAD值");
        ((ConcurrentHashMap<String, String>) THREAD_LOCAL.get()).forEach((k,v) -> {
            log.info("key为：{}；value为：{}", k, v);
        });
    }
    @Override
    public void definedMethods() throws InterruptedException {
        /** 通过获取父类的引用修改父类的值 */
        ConcurrentHashMap<String, String> map = (ConcurrentHashMap<String, String>) THREAD_LOCAL.get();
        map.put(str[atomicInteger.get()], "hello");
        atomicInteger.incrementAndGet();
    }


}
