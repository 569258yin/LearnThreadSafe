package com.yh.thread.synchromized;

/**
 * Created by kevinyin on 2017/7/20.
 */
public class MutanlePoint {
    public int x,y;

    public MutanlePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MutanlePoint(MutanlePoint mutanlePoint) {
        this.x = mutanlePoint.x;
        this.y = mutanlePoint.y;
    }
}
