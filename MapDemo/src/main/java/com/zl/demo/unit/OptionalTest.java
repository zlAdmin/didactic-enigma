package com.zl.demo.unit;

import java.util.Optional;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.demo.unit
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: zhanglie
 * @CreateDate: 2018/9/10 17:10
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/10 17:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class OptionalTest {
    public static void main(String[] args) {
        //生成一个空实例
        Optional<String> emty = Optional.empty();
        //构造一个name的Optional
        Optional<String> name = Optional.of("name");
        //生成一个null
        String test = null;
        /*Optional.ofNullable(test).orElseThrow(() -> new NoSuchElementException());
        String email = Optional.ofNullable(test)
                .map(n -> n).orElse("ssss");*/

        Optional<String> result = Optional.ofNullable("name@123")
                .filter(u -> u != null && u.contains("@"));
        System.out.println(result.isPresent());

        //String ss = Optional.ofNullable(test).orElse("hello word");

        //System.out.println(ss);

        /*if (emty.isPresent()){
            System.out.println("不为空");
        }else{
            System.out.println("为空");
        }
        emty.ifPresent(s -> System.out.println("值为：" + s));
        name.ifPresent(n -> System.out.println("值为：" + n));*/




    }
}
