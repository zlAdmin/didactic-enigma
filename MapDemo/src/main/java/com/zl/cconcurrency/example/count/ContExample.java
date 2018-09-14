package com.zl.cconcurrency.example.count;


import com.zl.cconcurrency.annoations.NotThreadSafe;
import com.zl.cconcurrency.example.temp.AbstractCountDownLunchComm;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

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
@NotThreadSafe
public class ContExample extends AbstractCountDownLunchComm{
    /** 请求总数**/
    public static  int clientTotal = 5000;
    /** 同时并发执行的线程数 **/
    public static  int threadTotal = 200;

    public static  int count = 0;

    public static void main(String[] args) throws Exception {

        new ContExample().execute(clientTotal,threadTotal);
        log.info("count:{}",count);
    }


    @Override
    public void definedMethods() {
        count++;
    }
}
