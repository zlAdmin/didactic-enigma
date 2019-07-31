package com.zl.guava.comparison;

import com.google.common.collect.ComparisonChain;
import lombok.Getter;
import lombok.Setter;

/**
 * 对象之间的比较
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-24 18:27
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
@Getter
@Setter
public class Person implements Comparable<Person> {
    private String name;
    private Integer age;

    /**
     * @return int
     * @throws
     * @Description ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略。
     * @Author zhanglei
     * @Date 18:42 2019/7/24
     * @Param [that]
     **/
    @Override
    public int compareTo(Person that) {
        return ComparisonChain.start()
                .compare(this.age, that.age)
                .compare(this.name, that.name).result();
    }
}
