package com.zl.concurrency.example.efficientcache;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能表现（Base）
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-19 21:15
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class ExpensiveFunction implements BaseComputable<String , BigInteger>{
    private AtomicInteger calculationTimes = new AtomicInteger(0);

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        Thread.sleep(1000);
        calculationTimes.incrementAndGet();
        return new BigInteger(arg).add(new BigInteger("10"));
    }

    public AtomicInteger getCalculationTimes() {
        return calculationTimes;
    }

    public void setCalculationTimes(AtomicInteger calculationTimes) {
        this.calculationTimes = calculationTimes;
    }
}