package com.yh.tools.cached;

import java.math.BigInteger;
import java.util.Random;

/**
 * 3ʵ�ֻ����������ӳٷǳ�С�����Ҳ�����й����߳�ͬʱִ�С�
 * ��������δ���м����������м��͵ļ��ʳ�����ͬ�¼����У����ڴ��future��map��ʱ���ڣ�
 * ��Ϊ�Ǹ��ϲ��������ܱ�֤��ԭ����
 * �ɲο�4ʵ��
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

    /**
     * 100���߳�����ǳ��죬���1��2
     * result
     *
     Thread[Thread-5-7,5,main]
     ����[7]ִ����3000ms���Ϊ:7
     ����[9]ִ����2986ms���Ϊ:9  ���Է�����ͬ����������һ����ִ��ʱ��ǳ������
     ����[9]ִ����2986ms���Ϊ:9
     ����[9]ִ����2986ms���Ϊ:9
     ����[9]ִ����2986ms���Ϊ:9
     ����[9]ִ����2986ms���Ϊ:9
     ����[9]ִ����2989ms���Ϊ:9
     ����[9]ִ����2986ms���Ϊ:9
     ����[9]ִ����2986ms���Ϊ:9
     ����[9]ִ����2994ms���Ϊ:9
     ����[9]ִ����2994ms���Ϊ:9
     ����[9]ִ����2994ms���Ϊ:9
     */

}
