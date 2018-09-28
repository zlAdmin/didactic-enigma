package com.zl.demo.test;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.demo.unit
 * @ClassName: ${TYPE_NAME}
 * @Description: 测试jvm内存相关
 * @Author: zhanglie
 * @CreateDate: 2018/9/9 17:42
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/9 17:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class TestMemory {
    public static void main(String[] args){

        /* 返回Java虚拟机试图使用的最大内存量 */
        long maxMemory = Runtime.getRuntime().maxMemory();

        /* 返回Java虚拟机中的内存总量 */
        Long totalMemory = Runtime. getRuntime().totalMemory();

        System.out.println("MAX_MEMORY ="+maxMemory +"(字节)、"+(maxMemory/(double)1024/1024) + "MB");

        System.out.println("TOTAL_ MEMORY = "+totalMemory +"(字节)"+(totalMemory/(double)1024/1024) + "MB");

    }


}
