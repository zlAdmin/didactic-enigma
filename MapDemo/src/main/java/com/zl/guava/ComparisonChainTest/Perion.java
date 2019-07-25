package com.zl.guava.ComparisonChainTest;

import com.google.common.collect.ComparisonChain;

/**
 * 对象之间的比较
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-24 18:27
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public class Perion implements Comparable<Perion> {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return int
     * @throws
     * @Description ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略。
     * @Author zhanglei
     * @Date 18:42 2019/7/24
     * @Param [that]
     **/
    @Override
    public int compareTo(Perion that) {
        return ComparisonChain.start()
                .compare(this.age, that.age)
                .compare(this.name, that.name).result();
    }
}
