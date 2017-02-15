package com.yh.thread.readwritelock;

public class ReadThread extends Thread{
	private final TData data;
	public ReadThread(TData data) {
		this.data = data;
	}
	
	@Override
	public void run() {
		try {
			while(true){
				char[] readbuf = data.read();
				System.out.println(Thread.currentThread().getName() + 
						" reads "+String.valueOf(readbuf));
 			}
		} catch (Exception e) {
			System.out.println("Read Error");
		}
	}
}
