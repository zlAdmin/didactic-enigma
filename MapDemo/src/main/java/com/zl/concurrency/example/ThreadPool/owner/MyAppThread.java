package com.zl.concurrency.example.ThreadPool.owner;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-25 21:56
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class MyAppThread extends Thread {
    private static final String DEFAULT_NAME = "ZL_Thread";
    private static volatile  boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public MyAppThread(Runnable runnable){
        this(runnable, DEFAULT_NAME);
    }
    public MyAppThread(Runnable runnable, String poolName) {
        super(runnable, poolName + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.log(Level.SEVERE, "UNCAUGHT in the Thread" + t.getName(), e);
            }
        });
    }

    @Override
    public void run() {
        boolean debug = debugLifecycle;
        if (debug) {
            LOGGER.log(Level.FINE, "Created" + getName());
        }

        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                LOGGER.log(Level.FINE, "Exiting" + getName());
            }
        }
    }

    public static int getThradsCreated() {
        return created.get();
    }

    public static int getThreadsAlive() {
        return alive.get();
    }

    public static boolean getDebug(){
        return debugLifecycle;
    }

    public static void setDebug(boolean debug) {
        debugLifecycle = debug;
    }
}
