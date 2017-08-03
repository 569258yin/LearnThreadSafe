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
     ����[7]ִ����3ms���Ϊ:7
     ����[1]ִ����3ms���Ϊ:1
     ����[2]ִ����3ms���Ϊ:2   ���ظ���������֣��Һ�ʱ
     ����[2]ִ����3ms���Ϊ:2
     ����[2]ִ����3ms���Ϊ:2
     ����[3]ִ����3ms���Ϊ:3
     ����[6]ִ����3ms���Ϊ:6
     ����[4]ִ����4ms���Ϊ:4
     ����[8]ִ����4ms���Ϊ:8
     ����[8]ִ����1ms���Ϊ:8
     ����[9]ִ����1ms���Ϊ:9
     ����[1]ִ����1ms���Ϊ:1
     ����[6]ִ����7ms���Ϊ:6
     ����[1]ִ����3ms���Ϊ:1
     Thread[Thread-1-6,5,main]
     */

}
