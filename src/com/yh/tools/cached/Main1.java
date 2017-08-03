package com.yh.tools.cached;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by kevinyin on 2017/8/3.
 */
public class Main1 {

    private static Random random = new Random();

    public static void main(String[] args) {
        final Computable<String,BigInteger> c = new ExpensiveFunction();
        final Memoizer1 m1 = new Memoizer1(c);
        for (int i = 0; i < 100; i++) {
            final int arg =  random.nextInt(10);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long startTime = System.currentTimeMillis();
                        BigInteger result = (BigInteger) m1.compute(arg+"");
                        long endTime = System.currentTimeMillis();
                        System.out.println("����["+arg+"]ִ����"+ (endTime-startTime) + "ms"
                                + "���Ϊ:"+result);
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
     ����[4]ִ����4001ms���Ϊ:4   ִ������ͬ������
     ����[4]ִ����2001ms���Ϊ:4
     ����[7]ִ����3000ms���Ϊ:7
     ����[4]ִ����0ms���Ϊ:4
     ����[8]ִ����2000ms���Ϊ:8
     ����[9]ִ����2001ms���Ϊ:9
     ����[2]ִ����3000ms���Ϊ:2
     ����[2]ִ����3000ms���Ϊ:2
     ����[4]ִ����4001ms���Ϊ:4
     ����[6]ִ����0ms���Ϊ:6
     ����[9]ִ����3000ms���Ϊ:9
     ����[9]ִ����2001ms���Ϊ:9
     ����[4]ִ����2001ms���Ϊ:4
     ����[7]ִ����1000ms���Ϊ:7
     */

}
