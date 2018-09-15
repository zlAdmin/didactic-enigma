package com.zl.concurrency.example.singleton;

import com.zl.concurrency.annoations.NotRecommend;
import com.zl.concurrency.annoations.ThreadSafe;

/**
 * 单例模式方式一（懒汉模式）
 *（不推荐，性能开销）
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 13:25
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@ThreadSafe
@NotRecommend
public class ThreadSafeLazySingle {
    private ThreadSafeLazySingle(){

    }
    /** 静态实例 **/
    private static ThreadSafeLazySingle instance;

    /** 静态工厂方法 **/
    public static synchronized ThreadSafeLazySingle getInstance(){
        if(instance == null) {
            instance = new ThreadSafeLazySingle();
        }
        return instance;
    }
}
