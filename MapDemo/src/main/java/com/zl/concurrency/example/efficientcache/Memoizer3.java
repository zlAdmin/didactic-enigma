package com.zl.concurrency.example.efficientcache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 实现方案2（改进思路：将ConcurrentHashMap的Value换成FutureTask）
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-19 21:20
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class Memoizer3<A,V> implements BaseComputable<A,V>  {
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final BaseComputable<A,V> c;

    public Memoizer3(BaseComputable<A,V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                   return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, f);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
/**
 * 方案三是一个几乎完美的实现，但是还存在的问题：即if（f == null）这段代码并不是原子性的操作，所有会出现线程2查询f1不存在，应为f1还没执行put，
 * 但是此时线程1过来查询已经执行到put的前一步骤，还是会导致同一结果计算两次的情况
 *
 */
