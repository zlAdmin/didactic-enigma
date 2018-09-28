package com.zl.collection.arraylisttest;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.concurrency.example
 * @ClassName: ${TYPE_NAME}
 * @Description: ArrayList迭代
 * @Author: zhanglie
 * @CreateDate: 2018/9/13 18:34
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/13 18:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class ArrayListTest {
    private static Collection list = new ArrayList<>();

    public static void main(String[] args) {
        list.add("hello5");
        list.add("hello4");
        list.add("hello3");
        list.add("hello2");
        Iterator a = list.iterator();
        while (a.hasNext()) {
            System.out.println(a.next());
            a.remove();
        }
        System.out.println(list.size());
    }
}
