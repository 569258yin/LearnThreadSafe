package com.yh.thread.readwritelock;

public class MyReadWriteLock {

	/** 实际正在读取的线程数量 */
	private int readingReaders = 0;
	/** 正在等待写入的线程数量 */
	private int waitingWriters = 0; 
	/** 实际正在写入的线程数量 */
	private int writingWriter = 0;
	/** 写入优先的话，为true 
	 * <P>
	 * 此参数意义非常重要，为了解决读或写之间有一个拿到锁定后，长时间不会释放，而导致另一个永远无法执行
	 * */
	private boolean preferWriter = true;

	public synchronized void readLock() throws InterruptedException {
		while(writingWriter>0 || (preferWriter && waitingWriters > 0)){
			wait();
		}
		//实际正在读取的线程数量+1
		readingReaders++;
	}

	public synchronized void readUnlock(){
		//实际正在读取的线程数量-1
		readingReaders--;
		preferWriter = true;
		notifyAll();
	}


	public synchronized void writeLock() throws InterruptedException {
		//正在等待写入的线程+1
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
