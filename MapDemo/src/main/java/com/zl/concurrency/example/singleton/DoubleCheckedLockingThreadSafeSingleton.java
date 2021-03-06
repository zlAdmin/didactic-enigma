package com.zl.concurrency.example.singleton;


import com.zl.concurrency.annoations.Recommend;
import com.zl.concurrency.annoations.ThreadSafe;

/**
 * 单例模式方式一（懒汉模式）==>双重同步锁单例模式
 *（多线程会出现问题）
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 13:25
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@ThreadSafe
@Recommend
public class DoubleCheckedLockingThreadSafeSingleton {
    private DoubleCheckedLockingThreadSafeSingleton(){ }

    /**
     * jvm底层对象实例化过程
     * 1、memory = allocate() 分配对象的内存空间
     * 2、cotrInstance() 初始化对象
     * 3、instance = momory 设置instance指向刚分配的内存
     */
    //jvm和cpu优化，发生了指令重排
    /**
     * jvm底层对象发生指令重排后的可能实例化过程
     * 1、memory = allocate() 分配对象的内存空间
     * 3、instance = momory 设置instance指向刚分配的内存
     * 2、cotrInstance() 初始化对象
     */

    //所以多线程中可能出现：线程A执行到1、3，没有执行2，但时此时线程B进来判断instance!= null,直接返回尚未初始化的对象


    /** 静态实例  =>使用volatile限制指令重排 **/
    private volatile static DoubleCheckedLockingThreadSafeSingleton instance;

    /** 静态工厂方法 **/
    public static DoubleCheckedLockingThreadSafeSingleton getInstance(){
        //双重检测机制
        if(instance == null) {
            synchronized (DoubleCheckedLockingThreadSafeSingleton.class) {
                if(instance == null) {
                    instance = new DoubleCheckedLockingThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
