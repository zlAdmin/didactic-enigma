package com.zl.concurrency.example.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阿里的TransmittableThreadLocal在使用线程池等会池化复用线程的组件情况下传递ThreadLocal
 *
 * 解决的问题：除了拥有InheritableThreadLocal的功能外，解决了InheritableThreadLocal线程池引发的问题
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
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        Person person = new Person("hello word");
        THREAD_LOCAL.set(person);
        log.info(((Person)THREAD_LOCAL.get()).getName());
        TransmittableThreadLocalExample transmittableThreadLocalExample = new TransmittableThreadLocalExample();
        transmittableThreadLocalExample.execute(1, 4);
        log.info("父线程获取TEST_IN_THREAD值：{}", ((Person)THREAD_LOCAL.get()).getName());

    }
    @Override
    public void definedMethods() throws InterruptedException {
        log.info(Thread.currentThread().getName());
        log.info("线程{}获取到的值是：{}", Thread.currentThread().getName(), THREAD_LOCAL.get());
        /** 通过获取父类的引用修改父类的值 */
        Person person = (Person)THREAD_LOCAL.get();
        person.setName("hello java");
    }

    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
