package com.yh.tools.cached;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by kevinyin on 2017/8/3.
 */
public class Main3 {

    private static Random random = new Random();

    public static void main(String[] args) {
        final Computable<String,BigInteger> c = new ExpensiveFunction();
        final Memoizer3 m1 = new Memoizer3(c);
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

    /** result
     *
     任务[7]执行了3ms结果为:7
     任务[1]执行了3ms结果为:1
     任务[2]执行了3ms结果为:2   有重复的任务出现，且耗时
     任务[2]执行了3ms结果为:2
     任务[2]执行了3ms结果为:2
     任务[3]执行了3ms结果为:3
     任务[6]执行了3ms结果为:6
     任务[4]执行了4ms结果为:4
     任务[8]执行了4ms结果为:8
     任务[8]执行了1ms结果为:8
     任务[9]执行了1ms结果为:9
     任务[1]执行了1ms结果为:1
     任务[6]执行了7ms结果为:6
     任务[1]执行了3ms结果为:1
     Thread[Thread-1-6,5,main]
     */

}
