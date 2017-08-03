package com.yh.tools.cached;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用ConcurrentHashMap，但仍有问题，就是当某一个运算正在进行儿耗时很大时，
 * 会有其他线程也重复执行这个任务
 * Created by kevinyin on 2017/8/3.
 */
public class Memoizer2<A, V> implements Computable<A, V> {

    //************************使用线程安全的集合存储
    private final Map<A,V> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg,result);
        }
        System.out.println(Thread.currentThread());
        return result;
    }
}
