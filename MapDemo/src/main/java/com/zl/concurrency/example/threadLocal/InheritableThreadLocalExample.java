package com.zl.concurrency.example.threadLocal;

import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

/**
 * InheritableThreadLocal测试ThreadLocal父子线程传递实现方案
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-04 16:05
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class InheritableThreadLocalExample extends AbstractCountDownLunchComm {
    private static final InheritableThreadLocal<String> TEST_IN_THREAD = new InheritableThreadLocal();
    private static final ThreadLocal<String> TEST_THREAD_LOCAL = ThreadLocal.withInitial(() -> "123");

    public static void main(String[] args) throws Exception {
        TEST_IN_THREAD.set("Hello World");
        TEST_THREAD_LOCAL.set("Hello JAVA");
        InheritableThreadLocalExample example = new InheritableThreadLocalExample();
        example.execute(20, 4);
    }


    @Override
    public void definedMethods() throws InterruptedException {
        Thread.currentThread().getName();
        log.info(TEST_IN_THREAD.get());
        TEST_IN_THREAD.set("hello");
        //log.info(TEST_THREADLOCAL.get());

    }
}
