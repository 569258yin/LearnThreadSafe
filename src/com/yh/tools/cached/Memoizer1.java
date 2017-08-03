package com.yh.tools.cached;

import java.util.HashMap;
import java.util.Map;

/**
 * ������������
 * ����synchronized�����߳�ͬ��
 * Created by kevinyin on 2017/8/3.
 */
public class Memoizer1<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    // ************* synchronizedȷ���߳��ǰ�ȫ�ģ�����Ч�ʺܵͣ�ֻ����һ���߳�ִ�д˷���
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
