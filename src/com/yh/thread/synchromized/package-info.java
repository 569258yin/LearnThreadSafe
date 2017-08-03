/**
 *
 * 对于基本变量 long,double不是线程安全的，含有64位
 *
 * 当单个操作是线程安全时，并不能保证组合操作是线程安全的  pu-if-absent
 *
 * 对已经线程安全的类进行扩展时，应注意保证其线程安全持有相同的锁。
 *
 *
 * Created by kevinyin on 2017/7/20.
 */
package com.yh.thread.synchromized;
