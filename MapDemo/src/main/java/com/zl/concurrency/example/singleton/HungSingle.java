package com.zl.concurrency.example.singleton;

import com.zl.concurrency.annoations.NotRecommend;
import com.zl.concurrency.annoations.ThreadSafe;

/**
 * 单例模式方式一（饿汉模式）
 *（如果构造方法中有过多的处理会导致该方法加载很慢，性能问题）
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 13:25
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@ThreadSafe
@NotRecommend
public class HungSingle {
    private HungSingle(){

    }
    /** 静态实例 **/
    private static HungSingle instance = new HungSingle();

    /** 静态工厂方法 **/
    public static HungSingle getInstance(){
        return instance;
    }
}
