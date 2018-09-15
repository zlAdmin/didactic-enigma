package com.zl.concurrency.example.singleton;

import com.zl.concurrency.annoations.NotThreadSafe;
import com.zl.concurrency.annoations.Recommend;

/**
 * 单例模式方式一（懒汉模式）
 *（多线程会出现问题）
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 13:25
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@NotThreadSafe
@Recommend
public class LazySingle {
    private LazySingle(){

    }
    /** 静态实例 **/
    private static LazySingle instance;

    /** 静态工厂方法 **/
    public static LazySingle getInstance(){
        if(instance == null) {
            instance = new LazySingle();
        }
        return instance;
    }
}
