package com.yh.thread.future;

public class Main {

	public static void main(String[] args) {
		System.out.println("main BEGIN");
		Host host = new Host();
		TData data1 = host.request(10,'A');
		TData data2 = host.request(20,'B');
		TData data3 = host.request(30,'C');
		System.out.println("main otherJob BEGIN");
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		
		System.out.println("data1 =" + data1.getContent());
		System.out.println("data1 =" + data2.getContent());
		System.out.println("data1 =" + data3.getContent());
		System.out.println("main END");
	}

}
