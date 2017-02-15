package com.yh.active.activeobject;

/**
 * ���ͻ��˵��ã�ִ�����������ؽ����
 * ���÷���   --  ִ������ �еĵ��û���
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
