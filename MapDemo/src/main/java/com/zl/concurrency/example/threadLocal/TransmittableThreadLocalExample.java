package com.zl.concurrency.example.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里的TransmittableThreadLocal在使用线程池等会池化复用线程的组件情况下传递ThreadLocal
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-05 10:24
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class TransmittableThreadLocalExample extends AbstractCountDownLunchComm {
    private static final TransmittableThreadLocal THREAD_LOCAL = new TransmittableThreadLocal();

    public static void main(String[] args) {

    }
    @Override
    public void definedMethods() throws InterruptedException {
        log.info(Thread.currentThread().getName());
    }
}
