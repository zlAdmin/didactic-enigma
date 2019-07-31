package com.zl.guava.ordering;

import lombok.Getter;
import lombok.Setter;

/**
 * 排序类
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-26 15:52
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
@Setter
@Getter
public class Student {
    Student(String sortedBy){
        this.sortedBy = sortedBy;
    }
    private String sortedBy;
}
