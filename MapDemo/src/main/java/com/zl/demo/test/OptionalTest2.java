package com.zl.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


/**
 * 测试2
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-10-19 17:39
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class OptionalTest2 {
    public static void main(String[] args) {
        String str = "0";
        Optional optional = Optional.ofNullable(str).filter(u -> u.equals("1") || u.equals("0"));
        if (optional.isPresent()) {
            System.out.println("校验通过");
        }
    }

    private static void testInit(){
        Optional<String> empty = Optional.empty();
        Optional optional = Optional.ofNullable(null);
        Optional<String> name = Optional.of("name");


    }
}
