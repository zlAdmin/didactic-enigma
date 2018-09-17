package com.zl.concurrency.example.sync;

import com.zl.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * Vector集合测试实例
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-16 19:27
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@NotThreadSafe
public class VectorExample {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i < vector.size(); i++) {
                vector.add(i);
            }
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
