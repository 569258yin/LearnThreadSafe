package com.yh.tools.cached;

import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * 使用ConcurrentHashMap+ future 组合使用，future可先将结果的引用放在map
 * 使用putIfAbsent方法保证添加进去的原子性。防止放入重复
 *
 * future 方式有缓存污染问题（当某个计算过程失败或取消时，future仍在map中，后续的
 * 计算都不会进行实际的操作，导致均失败），需要经期从集合中移除。
 *
 * Created by kevinyin on 2017/8/3.
 */
public class Memoizer4<A, V> implements Computable<A, V> {

    //************************使用线程安全的集合存储
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
                //此处使用了concurentHashMap中的原子操作方法
                f = cache.putIfAbsent(arg,ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                System.out.println(Thread.currentThread());
                //此处会阻塞至任务完成
                return f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
