package com.yh.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �̳߳س�ʼ��������
 * Created by kevinyin on 2017/8/5.
 */
public class TestExecutorPool {



    public static void main(String[] args) {
        //���л������߳�ִ��
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //�̶���С
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        //���л��湦�ܣ��ɶ�̬���������̵߳ĸ���
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        //���������ӳ������ʱ����ʹ��
        ExecutorService executorService4 = Executors.newScheduledThreadPool(5);
    }

}
