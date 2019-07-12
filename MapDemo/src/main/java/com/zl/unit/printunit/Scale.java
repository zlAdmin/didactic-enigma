package com.zl.unit.printunit;

/**
 * 进制之间的转换
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-06-27 15:11
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public class Scale {
    public static final char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        String scale = toScale(10);
        System.out.println(scale);
    }


    public static String toScale(int num) {
        //十进制转16进制
        if (num == 0) {
            return "0";
        }
        String result = "";
        while (num != 0) {
            int x = num & 0xF;
            result = map[(x)] + result;
            num = (num >>> 4);
        }
        return result;
    }
}
