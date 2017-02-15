package com.yh.active.activeobject;

/**
 * ִ��������̣߳�����һ�����У���һֱ�Ӷ�����ȡ������ִ��
 * @author YH
 *
 */
public class ScheduleThread extends Thread{
	private final ActivationQueue queue;
	public ScheduleThread(ActivationQueue queue) {
		this.queue = queue;
	}

	public void invoke(MethodRequest request) {
		queue.putRequest(request);
	}
	
	@Override
	public void run() {
		while(true){
			MethodRequest request = queue.takeRequest();
			request.execute();
		}
	}

}
