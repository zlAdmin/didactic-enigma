package com.zl.concurrency.example.efficientcache;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现方案1（线程安全但是效率最差）
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-19 21:20
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class Memoizer1<A,V> implements BaseComputable<A,V>  {
    private final Map<A,V> cache = new HashMap<>();
    private final BaseComputable<A,V> c;

    public Memoizer1(BaseComputable<A,V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
/**
 * 由于HashMa是线程不安全的，因此要确保两个线程不会同时访问HashMap的保守方案，将该方案加锁处理，但是引发的问题：当某线程调用Compute时候，
 * 时间比较久，会一直阻塞线程，严重影响效率，可能为导致比没有缓存需要的时间更久
 *
 *
 */
