package com.yh.tools.cached;

import java.util.HashMap;
import java.util.Map;

/**
 * 将计算结果缓存
 * 利用synchronized进行线程同步
 * Created by kevinyin on 2017/8/3.
 */
public class Memoizer1<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    // ************* synchronized确保线程是安全的，但是效率很低，只能有一个线程执行此方法
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg,result);
        }
        System.out.println(Thread.currentThread());
        return result;
    }
}
