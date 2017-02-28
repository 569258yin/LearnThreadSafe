package com.yh.lock.reentrantlock;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * {@link http://ifeve.com/reentrantlock-and-fairness/}
 * 参考【并发编程网】  ReentrantLock(重入锁)以及公平性  代码
 * @author YH
 * 
 * 总结 ： 公平锁防止了某些线程由于一直得不到执行而造成饥饿状态，但降低了系统的吞吐量，而非公平锁相反
 */
public class ReentrantLockTest2 { 
	private static Lock fairLock = new ReentrantLock2(true);
	private static Lock unfairLock = new ReentrantLock2();

	/**
	 *  Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:1 and [0] waits.
		Lock by:0 and [1, 3, 4, 2] waits.   //从队列中取出最后一个,并将当前线程插入到队列中，采用链表的方式，每次取出排队时间最长的获取锁
		Lock by:2 and [0, 1, 3, 4] waits.
		Lock by:4 and [2, 0, 1, 3] waits.
		Lock by:3 and [4, 2, 0, 1] waits.
		Lock by:1 and [3, 4, 2, 0] waits.
		Lock by:0 and [1, 3, 4, 2] waits.
		Lock by:2 and [1, 3, 4] waits.
		Lock by:4 and [2, 1, 3] waits.
		Lock by:3 and [4, 2, 1] waits.
		Lock by:1 and [3, 4, 2] waits.
		Lock by:2 and [1, 3, 4] waits.
		Lock by:4 and [2, 1, 3] waits.
		Lock by:3 and [4, 2, 1] waits.
		Lock by:1 and [3, 4, 2] waits.
		Lock by:2 and [1, 3, 4] waits.
		Lock by:4 and [2, 1, 3] waits.
		Lock by:3 and [4, 2, 1] waits.
		Lock by:1 and [3, 4, 2] waits.
		Lock by:2 and [3, 4] waits.
		Lock by:4 and [3] waits.
		Lock by:3 and [] waits.
	 * 对于公平锁，基本上同一个线程连续获取锁的几率不是很多
	 */

	@Test
	public void fair(){
		System.out.println("fair Version");
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Job(fairLock)){
				@Override
				public String toString() {
					return getName();
				}
			};
			thread.setName(""+i);
			thread.start();
		}

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
	}

	/**
	 *  Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:4 and [1, 2] waits.
		Lock by:4 and [3, 1, 2] waits.
		Lock by:4 and [3, 1, 2] waits.
		Lock by:4 and [3, 1, 2] waits.
		Lock by:4 and [3, 1, 2] waits.
		Lock by:2 and [3, 1] waits.
		Lock by:2 and [3, 1] waits.
		Lock by:2 and [3, 1] waits.
		Lock by:2 and [3, 1] waits.
		Lock by:2 and [3, 1] waits.
		Lock by:1 and [3] waits.
		Lock by:1 and [3] waits.
		Lock by:1 and [3] waits.
		Lock by:1 and [3] waits.
		Lock by:1 and [3] waits.
		Lock by:3 and [] waits.
		Lock by:3 and [] waits.
		Lock by:3 and [] waits.
		Lock by:3 and [] waits.
		Lock by:3 and [] waits.
	 * 
	 * 对于非公平锁，同一个线程连续获取锁的几率比较大
	 */

	@Test
	public void unfair(){
		System.out.println("fair Version");
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Job(unfairLock)){
				@Override
				public String toString() {
					return getName();
				}
			};
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
					System.out.println("Lock by:"+Thread.currentThread().getName()
							+" and "+ ((ReentrantLock2)lock).getQueuedThreads() +" waits.");
				} finally {
					lock.unlock();
				}
			}
		}

	}

	private static class ReentrantLock2 extends ReentrantLock{
		private static final long serialVersionUID = -3461880628639554924L;

		public ReentrantLock2(boolean fair) {
			super(fair);
		}

		public ReentrantLock2() {
			super();
		}

		@Override
		protected Collection<Thread> getQueuedThreads() {
			return super.getQueuedThreads();
		}
	}

}
