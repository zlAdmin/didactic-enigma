package com.zl.cconcurrency.example;

import java.util.Optional;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.cconcurrency.example
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: zhanglie
 * @CreateDate: 2018/9/13 9:36
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/13 9:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class TestString {
    public static void main(String[] args) {
        String productName = null;
        productName = Optional.ofNullable(productName).orElse("123 ");
        System.out.println(productName);
        /*String str = "中国人民国好";
        if(str.length()>5){
            str = str.substring(0,5)  + "...";
        }*/

    }
}
