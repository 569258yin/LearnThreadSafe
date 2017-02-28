package com.yh.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * {@link http://ifeve.com/reentrantlock-and-fairness/}
 * �ο��������������  ReentrantLock(������)�Լ���ƽ��  ����
 * @author YH
 */
public class ReentrantLockTest {
	private static Lock fairLock = new ReentrantLock(true);
	private static Lock unfairLock = new ReentrantLock();
	
	/**
	 * ���ڹ�ƽ����������ͬһ���߳�������ȡ���ļ��ʲ��Ǻܶ�
	 */
	
	@Test
	public void fair(){
		System.out.println("fair Version");
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Job(fairLock));
			thread.setName(""+i);
			thread.start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
	}
	
	/**
	 * ���ڷǹ�ƽ����ͬһ���߳�������ȡ���ļ��ʱȽϴ�
	 */
	
	@Test
	public void unfair(){
		System.out.println("fair Version");
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Job(unfairLock));
			thread.setName(""+i);
			thread.start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
	}
	
	private static class Job implements Runnable{
		private Lock lock;
		
		public Job(Lock lock) {
			this.lock = lock;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				lock.lock();
				try {
					System.out.println("Lock by:"+Thread.currentThread().getName());
				} finally {
					lock.unlock();
				}
			}
		}
		
	}
	
}
