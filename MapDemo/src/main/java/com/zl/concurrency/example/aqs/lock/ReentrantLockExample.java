package com.zl.concurrency.example.aqs.lock;


import com.zl.concurrency.annoations.ThreadSafe;
import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.concurrency.code
 * @ClassName: ${TYPE_NAME}
 * @Description:ReentrantLock实现线程安全
 * @Author: zhanglie
 * @CreateDate: 2018/9/11 21:35
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/10/5 21:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Slf4j
@ThreadSafe
public class ReentrantLockExample extends AbstractCountDownLunchComm{
    /** 请求总数**/
    public static  int clientTotal = 5000;
    /** 同时并发执行的线程数 **/
    public static  int threadTotal = 200;

    public static  int count = 0;

    private static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws Exception {

        new ReentrantLockExample().execute(clientTotal,threadTotal);
        log.info("count:{}",count);
    }


    @Override
    public synchronized void definedMethods() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }

    }
}
