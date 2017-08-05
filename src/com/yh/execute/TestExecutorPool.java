package com.yh.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池初始化工具类
 * Created by kevinyin on 2017/8/5.
 */
public class TestExecutorPool {



    public static void main(String[] args) {
        //串行化，单线程执行
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //固定大小
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        //具有缓存功能，可动态调整池中线程的个数
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        //可以用于延迟任务或定时任务使用
        ExecutorService executorService4 = Executors.newScheduledThreadPool(5);
    }

}
