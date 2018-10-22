package com.zl.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap测试类
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-10-22 11:03
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class HashMapTest {
    public static void main(String[] args) {
        //testForReturn();
        //putNullKey();
        testHash();
    }

    /**
     * @Author zhanglei
     * @Description 验证HashMap的返回值（put时候，如果key不存在put成功返回null，否则替换之前的value值并返回之前的value值）
     * @Date 11:10 2018/10/22
     * @Param []
     * @return void
     **/
    private static void testForReturn(){
        Map<String, String> map = new HashMap(5);
        String value = map.put("key","value");
        log.info("值为" + value);
        String value2 = map.put("key","value1");
        log.info("值为：" + value2);
        String value3 = map.put("key","value2");
        log.info("值为：" + value3);
        if (map.containsValue("value2")) {
            log.info("值存在");
        }
    }

    /**
     * @Author zhanglei
     * @Description key和value都可以null
     * @Date 11:37 2018/10/22
     * @Param []
     * @return void
     **/
    private static void putNullKey() {
        Map<String, String> map = new HashMap(5);
        map.put(null, "123");
        log.info("值为1：" + map.get(null));
        map.put("1", null);
        log.info("值为2：" + map.get("1"));
    }

    private static void testHash() {
        String value = "testValue";
        log.info("输入1：" + value.hashCode());
        int a = hash(value);
        log.info("输出为：" + a);
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
