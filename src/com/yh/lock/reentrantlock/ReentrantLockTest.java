package com.yh.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * {@link http://ifeve.com/reentrantlock-and-fairness/}
 * 参考【并发编程网】  ReentrantLock(重入锁)以及公平性  代码
 * @author YH
 */
public class ReentrantLockTest {
	private static Lock fairLock = new ReentrantLock(true);
	private static Lock unfairLock = new ReentrantLock();
	
	/**
	 * 对于公平锁，基本上同一个线程连续获取锁的几率不是很多
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
	 * 对于非公平锁，同一个线程连续获取锁的几率比较大
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
