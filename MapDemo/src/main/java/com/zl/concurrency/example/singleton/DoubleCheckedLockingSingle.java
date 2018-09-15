package com.zl.concurrency.example.singleton;

import com.zl.concurrency.annoations.NotRecommend;
import com.zl.concurrency.annoations.NotThreadSafe;

/**
 * 单例模式方式一（懒汉模式）==>双重同步锁单例模式
 *（多线程会出现问题）
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 13:25
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@NotThreadSafe
@NotRecommend
public class DoubleCheckedLockingSingle {
    private DoubleCheckedLockingSingle(){ }

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


    /** 静态实例 **/
    private static DoubleCheckedLockingSingle instance;

    /** 静态工厂方法 **/
    public static DoubleCheckedLockingSingle getInstance(){
        //双重检测机制
        if(instance == null) {
            synchronized (DoubleCheckedLockingSingle.class) {
                if(instance == null) {
                    instance = new DoubleCheckedLockingSingle();
                }
            }
        }
        return instance;
    }
}
