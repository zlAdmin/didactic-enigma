package com.zl.demo.test;

import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * sdfd
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-18 20:40
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class BigDecimalTest {
    private final static SimpleDateFormat formatter = new  SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        String a = "3";

        Optional.ofNullable(a).filter(item -> "1".equals(item) || "2".equals(item)).orElseThrow(
                () -> new NoSuchElementException()
        );
    }
}