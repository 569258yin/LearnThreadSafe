package com.yh.thread.permessage;

/**
 * �첽���񷢲�
 * �߳�ִ�е�˳����Ҫ�� 
 * ������Ӧ�ԱȽϸߣ�������ִ��ȴ��Ҫ�ܳ�ʱ���ʱ��
 * ��������serverSocket ���յ�һ��socketʱ���������һ���µ��߳�ȥִ��
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
