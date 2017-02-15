package com.yh.thread.readwritelock;

public class Main {
	public static void main(String[] args) {
		TData data = new TData(10);
		new ReadThread(data).start();
		new ReadThread(data).start();
		new ReadThread(data).start();
		new ReadThread(data).start();
		new ReadThread(data).start();
		new ReadThread(data).start();
		new WriteThread(data, "ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
		new WriteThread(data, "abcdefghijklmnopqrstuvwxyz").start();
	}
}
