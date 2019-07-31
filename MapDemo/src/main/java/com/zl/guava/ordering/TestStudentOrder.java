package com.zl.guava.ordering;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-26 15:54
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
@Slf4j
public class TestStudentOrder {
    public static void main(String[] args) {
        Ordering<Student> ordering = Ordering.natural().nullsLast().onResultOf(new Function<Student, Comparable>() {
            @Nullable
            @Override
            public Comparable apply(@Nullable Student student) {
                return student.getSortedBy();
            }
        });
        List<Student> studentList = Lists.newArrayList(new Student("a"),
                new Student("c"),
                new Student("b"),
                new Student("g"),
                new Student("e"),
                new Student(null));
        Collections.sort(studentList, ordering);
        studentList.forEach(item -> log.info(item.getSortedBy()));
    }
}
