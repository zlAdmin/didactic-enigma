package com.zl.concurrency.example.object;

/**
 * Object类中的wait和notify功能验证
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-06 16:30
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
public class WaitNotifyTest {
    /** 在多线程间共享的对象上使用wait */
    private String[] shareObj = {"true"};

    public static void main(String[] args) {
        WaitNotifyTest test = new WaitNotifyTest();
        ThreadWait threadWait1 = test.new ThreadWait("wait thread1");
        threadWait1.setPriority(2);
        ThreadWait threadWait2 = test.new ThreadWait("wait thread2");
        threadWait2.setPriority(3);
        ThreadWait threadWait3 = test.new ThreadWait("wait thread3");
        threadWait3.setPriority(4);

        ThreadNotify threadNotify = test.new ThreadNotify("notify thread");


        threadWait1.start();
        threadWait2.start();
        threadWait3.start();
        threadNotify.start();
    }

    class ThreadWait extends Thread {

        public ThreadWait(String name) {
            super(name);
        }
        @Override
        public void run() {
            synchronized (shareObj) {
                System.out.println("线程" + this.getName() + "开始等待");
                long startTime = System.currentTimeMillis();
                try {
                    //该线程等待，同时释放shareObj的锁
                    shareObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("线程" + this.getName()
                        + "等待时间为：" + (endTime - startTime));

            }
            System.out.println("线程" + getName() + "等待结束");
        }
    }

    class ThreadNotify extends Thread {

        public ThreadNotify(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                // 给等待线程等待时间
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (shareObj) {
                System.out.println("线程" + this.getName() + "开始准备通知");
                shareObj.notifyAll();
                System.out.println("线程" + this.getName() + "通知结束");
            }
            System.out.println("线程" + this.getName() + "运行结束");
        }
    }
}
