package com.yh.tools.cached;

import java.math.BigInteger;
import java.util.Random;

/**
 * 3实现基本基本上延迟非常小，而且不会进行过多线程同时执行。
 * 但是由于未进行加锁处理，仍有极低的几率出现相同事件运行，即在存放future到map的时间内，
 * 因为是复合操作，不能保证其原子性
 * 可参考4实现
 * Created by kevinyin on 2017/8/3.
 */
public class Main2 {

    private static Random random = new Random();

    public static void main(String[] args) {
        final Computable<String,BigInteger> c = new ExpensiveFunction();
        final Memoizer2 m1 = new Memoizer2(c);
        for (int i = 0; i < 100; i++) {
            final int arg =  random.nextInt(10);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long startTime = System.currentTimeMillis();
                        BigInteger result = (BigInteger) m1.compute(arg+"");
                        long endTime = System.currentTimeMillis();
                        System.out.println("任务["+arg+"]执行了"+ (endTime-startTime) + "ms"
                                + "结果为:"+result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setName("Thread-"+i+"-"+arg);
            t.start();
        }
    }

    /**
     * 100个线程运算非常快，相比1，2
     * result
     *
     Thread[Thread-5-7,5,main]
     任务[7]执行了3000ms结果为:7
     任务[9]执行了2986ms结果为:9  可以发现相同任务阻塞在一起，且执行时间非常相近。
     任务[9]执行了2986ms结果为:9
     任务[9]执行了2986ms结果为:9
     任务[9]执行了2986ms结果为:9
     任务[9]执行了2986ms结果为:9
     任务[9]执行了2989ms结果为:9
     任务[9]执行了2986ms结果为:9
     任务[9]执行了2986ms结果为:9
     任务[9]执行了2994ms结果为:9
     任务[9]执行了2994ms结果为:9
     任务[9]执行了2994ms结果为:9
     */

}
