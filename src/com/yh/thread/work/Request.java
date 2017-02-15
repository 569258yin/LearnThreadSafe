package com.yh.thread.work;

import java.util.Random;

public class Request {
	private final String name;
	private final int number;
	private static final Random random = new Random();
	public Request(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}
	
	public void execute(){
		System.out.println(Thread.currentThread().getName() + " executes " +this);
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (Exception e) {
		}
	}

	@Override
	public String toString() {
		return "Request [name=" + name + ", number=" + number + "]";
	}
	
}
