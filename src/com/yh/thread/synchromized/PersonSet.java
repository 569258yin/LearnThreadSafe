package com.yh.thread.synchromized;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 实例封装，通过将线程不安全的数据进行封装在对象内部，并用正确的加锁机制，实现同步
 *
 * 通过封闭机制， 将可变数据对象封闭起来，缩小其访问域的范围，并在其可见的方法中加锁
 *
 * 参考  Collections.synchronizedList(List)  利用装饰器模式，将对象域实现封闭
 * * Created by kevinyin on 2017/7/20.
 */
public class PersonSet {
    private final Set<String> mySet = new HashSet<>();

    public synchronized void addPerson(String s){
        mySet.add(s);
    }

    public synchronized boolean containsStr(String s){
        return mySet.contains(s);
    }
}
