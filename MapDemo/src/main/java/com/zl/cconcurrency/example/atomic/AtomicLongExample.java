package com.zl.cconcurrency.example.atomic;


import com.zl.cconcurrency.annoations.ThreadSafe;
import com.zl.cconcurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

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
public class AtomicLongExample extends AbstractCountDownLunchComm {
    /** 请求总数 **/
    public static  int clientTotal = 5000;
    /** 同时并发执行的线程数  **/
    public static  int threadTotal = 200;

    public static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws Exception {
        AtomicLongExample atomicLongExample = new AtomicLongExample();
        atomicLongExample.execute(clientTotal,threadTotal);
    }
    @Override
    public void definedMethods() {
        count.incrementAndGet();
    }
}
