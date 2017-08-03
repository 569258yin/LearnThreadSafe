package com.yh.tools.cached;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ʹ��ConcurrentHashMap�����������⣬���ǵ�ĳһ���������ڽ��ж���ʱ�ܴ�ʱ��
 * ���������߳�Ҳ�ظ�ִ���������
 * Created by kevinyin on 2017/8/3.
 */
public class Memoizer2<A, V> implements Computable<A, V> {

    //************************ʹ���̰߳�ȫ�ļ��ϴ洢
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
