package com.zl.demo.unit;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;

import java.util.*;

/**
 * 布隆过滤器简单测试方法
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2019-01-08 21:26
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
@Slf4j
public class BloomFilterTest {
    private static final int COUNT = 10000;
    public static void main(String[] args) {
        int right = 0;
        int wrong = 0;
        //bit数组容量 和错误率
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),COUNT*2, 0.1);
        Map<String, Integer > map = new HashMap<>(COUNT);
        List<String> list = new ArrayList<>(COUNT);

        for (int i = 0; i < COUNT; i++) {
            String id = UUID.randomUUID().toString();
            bf.put(id);
            map.put(id, i);
            list.add(id);
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (bf.mightContain(value)) {
                if (map.containsKey(value)) {
                    right ++;
                } else {
                    wrong ++;
                }
            } else {
                if (map.containsKey(value)) {
                    wrong ++;
                }
            }
        }

        log.info("正确个数：{}",right);
        log.info("正确率是：{}",right/COUNT);
        log.info("错误率是：{}",wrong/COUNT);

    }
}
