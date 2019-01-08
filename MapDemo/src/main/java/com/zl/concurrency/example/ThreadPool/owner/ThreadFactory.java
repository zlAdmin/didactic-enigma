package com.zl.concurrency.example.ThreadPool.owner;

/**
 * 线程工厂接口
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-25 21:53
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public interface ThreadFactory {
    Thread newThread(Runnable runnable);
}
