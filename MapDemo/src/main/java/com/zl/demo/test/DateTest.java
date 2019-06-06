package com.zl.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 获取某段时间内的每天时间
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-10-31 9:44
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class DateTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

        String start = "2018-08-08";
        String end = "2018-08-14";

        Date dStart = null;
        Date dEnd = null;
        try {
            dStart = sdf.parse(start);
            dEnd = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Date> dateList = findDates(dStart, dEnd);
        for (Date date : dateList) {
            System.out.println(sdf.format(date));
        }
    }

    public static List<Date> findDates(Date dStart, Date dEnd) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dStart);

        List dateList = new ArrayList();
        //别忘了，把起始日期加上
        dateList.add(dStart);
        // 此日期是否在指定日期之后
        while (dEnd.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println(cStart.getTime().getTime());
            System.out.println(sdf.format(cStart.getTime()));
        }
        return dateList;
    }


}
