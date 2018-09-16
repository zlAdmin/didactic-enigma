package com.zl.demo.unit;

/**
 * 请求存放地址
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-16 12:13
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class RequestHolder {
    private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return  requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
