package com.zl.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-10-25 17:52
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class TestBigdecimal {
    public static void main(String[] args) {
        /*BigDecimal a = new BigDecimal(3);
        BigDecimal b = new BigDecimal(10);
        log.info(String.valueOf(b.divide(a)));*/
        //foramtCurrent();
        System.out.println(trans2(100000000.00));
    }
    private static void foramtCurrent(){
        DecimalFormat df = new DecimalFormat("#00,000.00");
        BigDecimal money = new BigDecimal(10000000000000.0089);
        String a = df.format(money);
        System.out.println(df.format(money));
    }
    private static String trans(BigDecimal num) {
         BigDecimal tenThousandYuan = new BigDecimal(10000);
        String[] unit = new String[] { "元","万", "千万", "亿" };
        for (String u : unit) {
            if (num.compareTo(tenThousandYuan) < 0){
                return String.format("%.2f%s", num, u);
            }
            num = num.divide(tenThousandYuan,4, RoundingMode.UP);
        }
        return "数据异常";
    }

    private static String trans2(double num) {
        String[] unit = new String[] {"元", "万", "千万", "亿" };
        for (String u : unit) {
            if (num < 10000) {
                return String.format("%.2f%s", num, u);
            }
            num /= 10000D;
        }
        return "无穷";
    }

}
