package com.zl.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.zl.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 不可变对象final测试
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-15 19:20
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class ImmutableFinalTest {
    private static final int anInt = 100;
    private static final String str = "hello word";
    private static final Map<Integer,Integer> map = Maps.newHashMap();

    static {
        /** 普通map类型虽然用final定义，但是其内容任然可以修改 **/
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map.put(7,8);
    }
}
