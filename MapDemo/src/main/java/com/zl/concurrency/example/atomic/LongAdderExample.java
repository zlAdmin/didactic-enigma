package com.zl.concurrency.example.atomic;


import com.zl.concurrency.annoations.ThreadSafe;
import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.LongAdder;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.concurrency.code
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: zhanglie
 * @CreateDate: 2018/9/11 21:35
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/11 21:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Slf4j
@ThreadSafe
public class LongAdderExample extends AbstractCountDownLunchComm {
    /**请求总数 **/
    private static  int clientTotal = 5000;
    /** 同时并发执行的线程数 **/
    private static  int threadTotal = 200;

    private static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception {
        LongAdderExample longAdderExample =new LongAdderExample();
        longAdderExample.execute(clientTotal,threadTotal);
        log.info("count:{}",count);
    }


    @Override
    public void definedMethods() {
            count.add(1);
    }
}
