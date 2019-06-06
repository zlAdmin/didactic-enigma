package com.zl.demo.test;

/**
 * 测试if的return
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-11-29 14:13
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class TestIf {
    public static void main(String[] args) {
        sayHelloHi();
    }


    private static void sayHelloHi () {
        if (1 == 1) {
            System.out.println("hello");
            return;
        }

        if (2 == 2) {
            System.out.println("hi");
        }
    }
}
