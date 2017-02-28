package com.yh.lock.reentrantlock;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * {@link http://ifeve.com/reentrantlock-and-fairness/}
 * �ο��������������  ReentrantLock(������)�Լ���ƽ��  ����
 * @author YH
 * 
 * �ܽ� �� ��ƽ����ֹ��ĳЩ�߳�����һֱ�ò���ִ�ж���ɼ���״̬����������ϵͳ�������������ǹ�ƽ���෴
 */
public class ReentrantLockTest2 { 
	private static Lock fairLock = new ReentrantLock2(true);
	private static Lock unfairLock = new ReentrantLock2();

	/**
	 *  Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:0 and [] waits.
		Lock by:1 and [0] waits.
		Lock by:0 and [1, 3, 4, 2] waits.   //�Ӷ�����ȡ�����һ��,������ǰ�̲߳��뵽�����У���������ķ�ʽ��ÿ��ȡ���Ŷ�ʱ����Ļ�ȡ��
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
	 * ���ڹ�ƽ����������ͬһ���߳�������ȡ���ļ��ʲ��Ǻܶ�
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
	 * ���ڷǹ�ƽ����ͬһ���߳�������ȡ���ļ��ʱȽϴ�
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
