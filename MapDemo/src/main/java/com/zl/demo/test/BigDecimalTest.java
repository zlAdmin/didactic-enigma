package com.zl.demo.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.zl.unit.printunit.PrintUnit.print;

/**
 * sdfd
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-18 20:40
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
public class BigDecimalTest {
    private final static SimpleDateFormat formatter = new  SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        String time = "2014-10-11";
        String time2 = "2014-10-13";
        Optional<Object> planStartTime = Optional.ofNullable("2014-10-1");
        Date timee = null;
        Date  timee2 = null;
        try {
            timee = formatter.parse((String) planStartTime.get());
            timee2 = formatter.parse(time2);
            System.out.println(timee);
            print(timee .before(timee2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BigDecimal bigNum1 = new BigDecimal("10");
        BigDecimal bigNum2 = new BigDecimal("5");

        print(bigNum1.divide(bigNum2));


    }
}
