package com.zl.concurrency.example.efficientcache;

/**
 * 基本接口
 *
 */
public interface BaseComputable<A, V> {
    V compute(A arg) throws InterruptedException;
}
