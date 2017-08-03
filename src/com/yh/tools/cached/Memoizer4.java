package com.yh.tools.cached;

import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * ʹ��ConcurrentHashMap+ future ���ʹ�ã�future���Ƚ���������÷���map
 * ʹ��putIfAbsent������֤��ӽ�ȥ��ԭ���ԡ���ֹ�����ظ�
 *
 * future ��ʽ�л�����Ⱦ���⣨��ĳ���������ʧ�ܻ�ȡ��ʱ��future����map�У�������
 * ���㶼�������ʵ�ʵĲ��������¾�ʧ�ܣ�����Ҫ���ڴӼ������Ƴ���
 *
 * Created by kevinyin on 2017/8/3.
 */
public class Memoizer4<A, V> implements Computable<A, V> {

    //************************ʹ���̰߳�ȫ�ļ��ϴ洢
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
    private final Computable<A,V> c;

    public Memoizer4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                //�˴�ʹ����concurentHashMap�е�ԭ�Ӳ�������
                f = cache.putIfAbsent(arg,ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                System.out.println(Thread.currentThread());
                //�˴����������������
                return f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
