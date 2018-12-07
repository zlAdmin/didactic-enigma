package com.zl.concurrency.example.aqs.lock;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condintion锁测试类
 * Condintion更精准的控制多线程的睡眠或唤醒(类似Object中的wait和notify方法，同样调用该方法需要先获取到ReentrantLock锁，ReentrantLock可以唤醒指定分组线程，但是Object唤醒是随机的)
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-14 19:05
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class ConditionExample {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        //一个ReentrantLock可以实例多个condition，通过对不同的condition的wait和signal实现对不同线程的等待和唤醒
        Condition condition = reentrantLock.newCondition();
        new Thread(()->{
            try {
                reentrantLock.lock();
                log.info("wait signal");
                condition.await();//调用await方法后，该线程将释放该锁，并且处于阻塞状态
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal");
            reentrantLock.unlock();
        }).start();

        new Thread(()->{
            reentrantLock.lock();
            log.info("get lock");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal -");
            reentrantLock.unlock();
        }).start();
    }
}
