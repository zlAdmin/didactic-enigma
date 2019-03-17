package com.zl.unit.printunit;

import lombok.var;

import java.io.File;

/**
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-01-08 14:58
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public class ToTestMakeDir {
    public static void main(String[] args) {
        System.out.println((In.String.value));
        System.out.println("Date".equals(In.Date.toString()));
        var s = new Double("10");

    }

    public enum In{
        String("123"),Date("233");
        String value;
        In(String s) {
            this.value = s;
        }
    }
}
