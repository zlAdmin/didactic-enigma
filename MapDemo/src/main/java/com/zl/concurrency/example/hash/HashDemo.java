package com.zl.concurrency.example.hash;

/**
 * 生产hash值
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-10 15:13
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class HashDemo {
    public static void main(String[] args) {

        String str = "/manage/product/product-lists";
        int a = str.hashCode();
        System.out.println(a);

    }
}
