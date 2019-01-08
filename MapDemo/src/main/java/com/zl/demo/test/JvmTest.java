package com.zl.demo.test;

import java.util.Random;
import static com.zl.unit.printunit.PrintUnit.print;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.demo.unit
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
public class JvmTest {
    public static void main(String[] args){

        /* 返回Java虚拟机试图使用的最大内存量*/
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* 返回Java虚拟机中的内存总量 */
        Long totalMemory = Runtime. getRuntime().totalMemory();

        print("MAX_MEMORY ="+maxMemory +"(字节)、"+(maxMemory/(double)1024/1024) + "MB");

        print("TOTAL_ MEMORY = "+totalMemory +"(字节)"+(totalMemory/(double)1024/1024) + "MB");

        testClassLoad();

    }


    /**
     * @Description 查看类的加载器方式
     * @return void
     * @throws 
     * @Author zhanglei
     * @Date 17:25 2018/12/21
     * @Param []
     **/
    private static void testClassLoad () {
        ClassLoader  c  = JvmTest.class.getClassLoader() ;
        ClassLoader  cl =  c.getParent( );
        ClassLoader  c2 =  cl . getParent() ;
    }


}
