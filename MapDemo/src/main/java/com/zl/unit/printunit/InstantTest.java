package com.zl.unit.printunit;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * JDK1.8新特性Instant测试
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-10-26 18:17
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
public class InstantTest {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        OffsetDateTime dateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(instant);
        System.out.println(dateTime);
    }
}
