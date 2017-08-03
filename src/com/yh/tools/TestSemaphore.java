package com.yh.tools;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量，可以用于集合的管理，比如数据库连接池，
 * acquire申请，relese释放，当Semaphore被申请完以后，再申请的线程会被阻塞
 * Created by kevinyin on 2017/8/3.
 */
public class TestSemaphore<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public TestSemaphore(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        }finally {
            if (!wasAdded){
                sem.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemove = set.remove(o);
        if (wasRemove) {
            sem.release();
        }
        return wasRemove;
    }


}
