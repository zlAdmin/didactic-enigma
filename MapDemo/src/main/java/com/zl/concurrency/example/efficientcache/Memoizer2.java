package com.zl.concurrency.example.efficientcache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现方案2（改进思路：将HashMap换成ConcurrentHashMap）
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-19 21:20
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class Memoizer2<A,V> implements BaseComputable<A,V>  {
    private final Map<A,V> cache = new ConcurrentHashMap<>();
    private final BaseComputable<A,V> c;

    public Memoizer2(BaseComputable<A,V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
/**
 * 存在问题：如果某个线程启动了一个开销很大的计算，二其他线程并不知道这个计算正在运行，很有可能会出现重复计算的可能；
 * 会导致一个结果重复计算的情况；
 *
 */
