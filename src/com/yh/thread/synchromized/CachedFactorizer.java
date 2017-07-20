package com.yh.thread.synchromized;

import java.math.BigInteger;

/**
 * Java 并发编程实战
 * 利用 synchronized 实现原子操作，并进行分断优化，
 * 因式分解 + cached
 * Created by kevinyin on 2017/7/15.
 */
public class CachedFactorizer {

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    //总访问数
    public synchronized long getHits() {
        return hits;
    }

    //得到cached的命中率
    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(BigInteger i) {
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            //在执行过长的时间之前释放锁
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{};
    }
}
