package com.yh.thread.safestop;

public class CountupThread extends Thread {
	private long counter = 0;
	//已经送出终止请求为true
	private volatile boolean shutdownRequested = false;
	
	//终止请求
	public void shutdownRequest(){
		shutdownRequested = true;
		interrupt();
	}
	
	public boolean isShutdownRequested(){
		return shutdownRequested;
	}
	
	public final void run(){
		try {
			while(!shutdownRequested){
				doWork();
			}
		} catch (Exception e) {
		}finally{
			doShutdown();
		}
	}

	private void doShutdown() {
		System.out.println("doShutdown: counter ="+counter);
	}

	private void doWork() throws InterruptedException {
		counter++;
		System.out.println("do Work: counter = "+counter);
		Thread.sleep(1000);
	}
	
	
	
}
