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
public class Memoizer4<A,V> implements BaseComputable<A,V>  {
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final BaseComputable<A,V> c;

    public Memoizer4(BaseComputable<A,V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);//达到每次只有一个Ft放入缓存成功的目的
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg);
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
/**
 * 通过ConcurrentHashMap的putIfAbsent的原子操作，
 *
 */
