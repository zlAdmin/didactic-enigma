package com.zl.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 通过同步屏障分线程计算求和
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-17 17:03
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class CyclicBarrierSum {
    /** 定义五个Future去保存子数组计算结果 */
    private static final int[] RESULTS = new int[5];

    /** 定义总数组长度 */
    private static final int SIZE = 50000;

    /** 定义子数组长度 */
    private static final int SUBARRAY = 1000;

    /** 定义固定的5个线程数量的线程池 */
    private static  ExecutorService executorService = Executors.newFixedThreadPool(5);

    /** 定义屏障以及唤醒线程之前执行的操作 */
    private static final  CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        int sums = 0;
        for (int i = 0; i < 5; i++) {
            sums += RESULTS[i];
        }
        log.info("多线程计算结果：" + sums);
    });

    public static void main(String[] args) {
        //定义数组
        int[] numbers = initArray();
        //定义五个线程去计算
        for (int i = 0; i < 5; i++) {
            //定义子数组
            int[] subNumbers = Arrays.copyOfRange(numbers, (i * SUBARRAY), ((i + 1) * SUBARRAY));
            //盛放计算结果
            int finalI = i;
            executorService.execute(()->{
                try {
                    sum(finalI, subNumbers);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        //关闭线程池
        executorService.shutdown();

    }

    private static int[] initArray(){
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (int)(Math.random()*1000+1);
        }
        return arr;
    }

    private static void sum(final int i,int[] subNumbers) throws Exception {
        for (int j = 0; j < subNumbers.length; j++) {
            RESULTS[i] += subNumbers[j];
        }
        barrier.await();
    }
}
