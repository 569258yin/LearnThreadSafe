package com.yh.active.activeobject;

/**
 * 执行任务的线程，管理一个队列，并一直从队列中取出任务执行
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
