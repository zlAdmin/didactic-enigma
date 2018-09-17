package com.zl.concurrency.example.sync;

import java.util.Iterator;
import java.util.Vector;

/**
 * 线程安全类Vector测试
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 20:05
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
public class VectorExample1 {
    private static void test1(Vector<Integer> v1){
        for (Integer i:v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }

    }
    private static void test2(Vector<Integer> v1){
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }

    }
    private static void test3(Vector<Integer> v1){
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }

    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
       // test1(vector);
        //test2(vector);
        test3(vector);
    }
}
