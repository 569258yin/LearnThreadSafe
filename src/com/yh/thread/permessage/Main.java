package com.yh.thread.permessage;

/**
 * 异步任务发布
 * 线程执行的顺序不做要求 
 * 用于响应性比较高，而任务执行却需要很长时间的时候
 * 常见的有serverSocket 接收到一个socket时，将其放入一个新的线程去执行
 * @author MyPC
 *
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("main BEGIN..");
		Host host = new Host();
		host.request(10,'A');
		host.request(20,'B');
		host.request(30,'C');
		System.out.println("main END");
	}
}
