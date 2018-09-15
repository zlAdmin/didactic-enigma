package com.zl.concurrency.example.singleton;

import com.zl.concurrency.annoations.Recommend;
import com.zl.concurrency.annoations.ThreadSafe;

/**
 * 用枚举实现单例模式
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 13:54
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@ThreadSafe
@Recommend
public class EnumThreadSafeSingleton {
    private EnumThreadSafeSingleton(){

    }
    public static EnumThreadSafeSingleton getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }
    private enum Singleton {
        INSTANCE;

        private EnumThreadSafeSingleton singleton;

        //JVM保证这个方法只被绝对调用一次
        Singleton() {
            singleton = new EnumThreadSafeSingleton();
        }
        public EnumThreadSafeSingleton getSingleton() {
            return singleton;
        }
    }
}
