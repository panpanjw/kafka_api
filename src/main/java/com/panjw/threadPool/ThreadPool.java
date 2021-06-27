package com.panjw.threadPool;

import java.util.concurrent.*;

/**
 * @author panjw
 * @date 2021/6/5 20:18
 */
public class ThreadPool {
    private ThreadPoolExecutor threadPoolExecutor;

    private int coreSize = 10;

    private int maxSize = 20;

    private long keepAliveTime = 1L;

    private TimeUnit timeUnit = TimeUnit.MINUTES;


    public ThreadPool(){
        threadPoolExecutor = new ThreadPoolExecutor(
                this.coreSize,
                this.maxSize,
                this.keepAliveTime,
                this.timeUnit,
                new LinkedBlockingQueue<Runnable>());
    }

    public ThreadPool(int coreSize, int maxSize, long keepAliveTime, TimeUnit timeUnit){
        threadPoolExecutor = new ThreadPoolExecutor(
                coreSize,
                maxSize,
                keepAliveTime,
                timeUnit,
                new LinkedBlockingQueue<Runnable>());
    }

    public Future<?> submit(Runnable runnable) {
       return threadPoolExecutor.submit(runnable);
    }

    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public void shutdown(){
        threadPoolExecutor.shutdown();
    }




}
