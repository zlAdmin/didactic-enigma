package com.zl.concurrency.example.ThreadPool.owner;

/**
 * 自定义线程工厂
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-25 21:54
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class MyThreadFactory implements ThreadFactory{
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }


    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
