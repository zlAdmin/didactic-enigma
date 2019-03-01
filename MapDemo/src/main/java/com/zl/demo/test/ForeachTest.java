package com.zl.demo.test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.demo.unit
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: zhanglie
 * @CreateDate: 2018/9/7 13:56
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/7 13:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class ForeachTest {
    public  static volatile boolean b = false;
    public  static  void main(String[] arg){
        ForeachTest.testhashMap();
    }
    private static void testhashMap(){
        System.out.print(System.currentTimeMillis());
        Map<String, String> map = new HashMap(2);
        map.put("hello","word");
        map.forEach((k,v)->{
            System.out.println("key : " + k + "; value : " + v);
        });
        List<String> list = new ArrayList(10);
        for (int i = 0; i < 10; i ++){
            Random random = new Random();
            int a = random.nextInt(10);
            String s = String.valueOf(a);
            list.add("hello"+s);
        }
        list.forEach((String i)-> {
            System.out.println(i);
        });
        //去重操作
        List newList = list.stream().distinct().collect(Collectors.toList());
        System.out.print(newList);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        System.out.print(random.nextInt(5));
    }

    private static void testThread(){


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int a = 0; a <= 100; a ++){
                    if (a==55){
                        b = true;
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while ( true ){
                    if (b){
                        System.out.print("hello word" + b);
                        Thread.yield();
                    }
                }
            }
        });

        thread.start();
        thread2.start();

    }
}
