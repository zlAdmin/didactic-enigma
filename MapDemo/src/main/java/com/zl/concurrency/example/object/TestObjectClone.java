package com.zl.concurrency.example.object;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试Object类中的clone方法，需要实现Cloneable接口
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-07 10:36
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class TestObjectClone implements Cloneable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected TestObjectClone clone() throws CloneNotSupportedException {
        return (TestObjectClone) super.clone();
    }

    public static void main(String[] args) {
        TestObjectClone testObjectClone = new TestObjectClone();
        testObjectClone.setName("java");
        testObjectClone.setAge(23);
        TestObjectClone testObjectClone2;
        try {
            testObjectClone2 = testObjectClone.clone();
            log.info("名称是{}，年龄是{}", testObjectClone2.getName(), testObjectClone2.getAge());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
