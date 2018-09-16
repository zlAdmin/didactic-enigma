package com.zl.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.zl.concurrency.annoations.ThreadSafe;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 不可变集合测试
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 19:33
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
@ThreadSafe
public class UnmodifiableMapTest {
    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //修改为collections的unmodifiableMap后修改会抛出异常
        map.put(1,3);
    }
}
