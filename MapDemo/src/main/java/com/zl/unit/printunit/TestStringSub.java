package com.zl.unit.printunit;

/**
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-04-15 10:19
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public class TestStringSub {
    public static void main(String[] args) {
        int startNo = 120;
        String format1 = "%0xd";
        String x = format1.replace("x", "10");
        String format = String.format(x, startNo);
        System.out.println(format);
    }
}
