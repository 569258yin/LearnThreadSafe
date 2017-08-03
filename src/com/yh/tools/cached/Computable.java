package com.yh.tools.cached;

/**
 * Created by kevinyin on 2017/8/3.
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
