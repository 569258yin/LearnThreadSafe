package com.yh.lock.reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by kevinyin on 2017/7/15.
 */
public class TestLock {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private String k;

    public void read(){
        try {
            lock.readLock().lock();
            System.out.println(k);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void write(){
        try {
            lock.writeLock().lock();

        }finally {
            lock.writeLock().unlock();
        }
    }


}
