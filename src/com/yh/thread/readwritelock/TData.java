package com.yh.thread.readwritelock;

import java.util.Arrays;

public class TData {
	private final MyReadWriteLock lock = new MyReadWriteLock();
	
	private final char[] buffer;
	
	public TData(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = '*';
		}
	}
	
	public char[] read() throws InterruptedException{
		lock.readLock();
		try {
			return doRead();
		} finally {
			lock.readUnlock();
		}
	}
	
	public void write(char c) throws InterruptedException{
		lock.writeLock();
		try {
			doWrite(c);
		} finally {
			lock.writeUnlock();
		}
	}

	private char[] doRead() {
//		char[] newbuf = new char[buffer.length];
//		for (int i = 0; i < buffer.length; i++) {
//			newbuf[i] = buffer[i];
//		}
		char[] newbuf;
		newbuf = Arrays.copyOf(buffer, buffer.length);
		slowly();
		return newbuf;
	}
	
	

	private void doWrite(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			slowly();
		}
	}
	
	private void slowly() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
