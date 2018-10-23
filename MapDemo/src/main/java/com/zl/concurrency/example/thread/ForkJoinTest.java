package com.zl.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-10-23 21:58
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class ForkJoinTest extends RecursiveTask<Integer> {
    public static final int threshold = 2;
    private int start;
    private int end;
    public ForkJoinTest(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canComputer = (end - start) <= threshold;
        //如果任务小可以计算
        if (canComputer) {
            for (int i = start; i <= end; i ++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.info("计算异常");
                }
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinTest leftTask = new ForkJoinTest(start, middle);
            ForkJoinTest rightTask = new ForkJoinTest(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        log.info("开始时间:{}", System.currentTimeMillis());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(1, 100);

        Future<Integer> result = forkJoinPool.submit(task);
        try {
            log.info("result：{}", result.get());
            log.info("结束时间:{}", System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            log.info("exception", e);
        }
    }
}
