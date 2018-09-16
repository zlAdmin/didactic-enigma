package com.zl.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.zl.concurrency.annoations.ThreadSafe;
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
public class ImmutableListTest {
    private static final ImmutableList list = ImmutableList.of(1,3,5,6,7,8,9);

    private static final ImmutableSet ser = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer,Integer> MAP = ImmutableMap.of(1,3,4,5);

    private static final ImmutableMap<Integer,Integer> buildMap = ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).build();


    public static void main(String[] args) {
        //以上集合修改都会抛异常
        list.add(4);

    }
}
