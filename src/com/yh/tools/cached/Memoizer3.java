package com.yh.tools.cached;

import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * ʹ��ConcurrentHashMap+ future ���ʹ�ã�future���Ƚ���������÷���map
 * ��ֹ���ظ��Ĳ�������
 * Created by kevinyin on 2017/8/3.
 */
public class Memoizer3<A, V> implements Computable<A, V> {

    //************************ʹ���̰߳�ȫ�ļ��ϴ洢
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
    private final Computable<A,V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg,ft);
            ft.run();
        }
        try {
            System.out.println(Thread.currentThread());
            //�˴����������������
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
