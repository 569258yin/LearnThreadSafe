package com.yh.thread.synchromized;

/**
 * 不可变对象是线程安全的
 * Created by kevinyin on 2017/7/20.
 */
public class Point {
    public final int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
