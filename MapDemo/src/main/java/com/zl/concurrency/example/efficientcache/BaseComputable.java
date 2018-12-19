package com.zl.concurrency.example.efficientcache;

/**
 * @author zhagnlei
 * 基本接口
 */
public interface BaseComputable<A, V> {
    /**
     * 自定义计算方法
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    V compute(A arg) throws InterruptedException;
}
