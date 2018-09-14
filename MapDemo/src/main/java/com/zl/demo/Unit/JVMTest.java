package com.zl.demo.Unit;

import java.util.Random;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.demo.Unit
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: zhanglie
 * @CreateDate: 2018/9/9 17:46
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/9 17:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class JVMTest {
    public static void main(String[] args){

        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机试图使用的最大内存量。

        Long totalMemory = Runtime. getRuntime().totalMemory();//返回Java虚拟机中的内存总量。

        System.out.println("MAX_MEMORY ="+maxMemory +"(字节)、"+(maxMemory/(double)1024/1024) + "MB");

        System.out.println("TOTAL_ MEMORY = "+totalMemory +"(字节)"+(totalMemory/(double)1024/1024) + "MB");

        String str = "www.baidu.com";

        while(true){

            str += str + new Random().nextInt(88888888) + new Random().nextInt(99999999);

        }

    }


}
