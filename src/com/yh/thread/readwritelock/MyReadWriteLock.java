package com.yh.thread.readwritelock;

public class MyReadWriteLock {

	/** ʵ�����ڶ�ȡ���߳����� */
	private int readingReaders = 0;
	/** ���ڵȴ�д����߳����� */
	private int waitingWriters = 0; 
	/** ʵ������д����߳����� */
	private int writingWriter = 0;
	/** д�����ȵĻ���Ϊtrue 
	 * <P>
	 * �˲�������ǳ���Ҫ��Ϊ�˽������д֮����һ���õ������󣬳�ʱ�䲻���ͷţ���������һ����Զ�޷�ִ��
	 * */
	private boolean preferWriter = true;

	public synchronized void readLock() throws InterruptedException {
		while(writingWriter>0 || (preferWriter && waitingWriters > 0)){
			wait();
		}
		//ʵ�����ڶ�ȡ���߳�����+1
		readingReaders++;
	}

	public synchronized void readUnlock(){
		//ʵ�����ڶ�ȡ���߳�����-1
		readingReaders--;
		preferWriter = true;
		notifyAll();
	}


	public synchronized void writeLock() throws InterruptedException {
		//���ڵȴ�д����߳�+1
		waitingWriters++;
		try{
			while(readingReaders > 0 || writingWriter > 0){
				wait();
			}
		}finally{
			waitingWriters--;
		}
		writingWriter++;
	}
	
	public synchronized void writeUnlock(){
		writingWriter--;
		preferWriter = false;
		notifyAll();
	}

}
