package com.yh.active.activeobject;

/**
 * 供客户端调用，执行完立即返回结果，
 * 调用方法   --  执行任务 中的调用环节
 * @author MyPC
 *
 */
public class Proxy implements ActiveObject{
	private final ScheduleThread scheduler;
	private final Servant servant;
	public Proxy(ScheduleThread scheduler, Servant servant) {
		this.scheduler = scheduler;
		this.servant = servant;
	}

	@Override
	public Result makeString(int count, char fillchar) {
		FutureResult future = new FutureResult();
		scheduler.invoke(new MakeStringRequest(servant,future,count,fillchar));
		return future;
	}

	@Override
	public void displayString(String string) {
		scheduler.invoke(new DisplayStringRequest(servant,string));
	}

}
