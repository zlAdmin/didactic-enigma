package com.zl.concurrency.example.atomic;


import com.zl.concurrency.annoations.ThreadSafe;
import com.zl.concurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

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
public class AtomicIntegerExample extends AbstractCountDownLunchComm {
    /** 请求总数 **/
    public static  int clientTotal = 10;
    /** 同时并发执行的线程数 **/
    public static  int threadTotal = 2;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        try {
            new AtomicIntegerExample().execute(clientTotal,threadTotal);
            log.info("输出：{}",count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("count:{}",count.get());
    }


    @Override
    public void definedMethods() throws InterruptedException {
        count.incrementAndGet();
        Thread.sleep(2000);
    }
}
