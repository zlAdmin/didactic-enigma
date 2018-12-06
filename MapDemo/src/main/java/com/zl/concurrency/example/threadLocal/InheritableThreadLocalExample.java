package com.zl.concurrency.example.threadLocal;

import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

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
    private static final ThreadLocal<String> TEST_THREAD_LOCAL = new ThreadLocal<>();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        log.info("主线程是：" + Thread.currentThread().getName());
        TEST_IN_THREAD.set("Hello World");
        TEST_THREAD_LOCAL.set("Hello JAVA");

        InheritableThreadLocalExample example = new InheritableThreadLocalExample();
        example.execute(20, 4);

        log.info("------------end------------");
        log.info("父线程获取TEST_IN_THREAD值：{}", TEST_THREAD_LOCAL.get());
        log.info("父线程获取TEST_THREAD_LOCAL值：{}", TEST_IN_THREAD.get());

    }


    @Override
    public void definedMethods() throws InterruptedException {
        log.info("子线程:{}获取父线程-TEST_IN_THREAD_THREAD:{}", Thread.currentThread().getName(), TEST_IN_THREAD.get());
        log.info("子线程:{}获取父线程-TEST_THREAD_LOCAL:{}", Thread.currentThread().getName(), TEST_THREAD_LOCAL.get());
        /** 子线程修改获取到的父线程变量 */
        TEST_IN_THREAD.set("hello"+ atomicInteger.incrementAndGet());
        log.info("子线程：{}修改后的的父线程变量：{}", Thread.currentThread().getName(), TEST_IN_THREAD.get());

        /** 测试TEST_IN_THREAD_THREAD，如果 */
    }
}

/**
 *  输入结果分析1：子线程中->TEST_IN_THREAD变量获取的是Hello Word，原因InheritableThreadLocal实现了获取父线程的线程变量；
 *                而TEST_THREAD_LOCAL获取为null，原因它并没有实现父线程变量的拷贝
 *  输入结果分析2：子线程修改自己的备份并没有在刷回父线程变量里面去
 *
 *  输出结果分析3：如果是使用线程池，在使用InheritableThreadLocal的时候，父线程A的子线程调用线程池线程a，a在使用完后并没有清除a线程中的threadLocal值，此时线程a被调用，导出取出的值仍然是之前值；会因为线程复用而获取到该线程之前的父线程的拷贝
 *
 *
 * */
