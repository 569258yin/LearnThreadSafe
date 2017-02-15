package com.yh.thread.readwritelock;

import java.util.Random;

public class WriteThread extends Thread{
	private static final Random random = new Random();
	private final TData data;
	private final String filler;
	private int index = 0;
	public WriteThread(TData data,String filler) {
		this.data = data;
		this.filler = filler;
	}
	
	@Override
	public void run() {
		try {
			while(true){
				char c = nextchar();
				System.out.println("Write a char c ='"+c+"'");
				data.write(c);
				Thread.sleep(random.nextInt(3000));
			}
		} catch (Exception e) {
			System.out.println("Write Error");
		}
	}

	private char nextchar() {
		char c = filler.charAt(index);
		index++;
		if(index >= filler.length()){
			index = 0;
		}
		return c;
	}
}
