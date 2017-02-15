package com.yh.active.activeobject;

/**
 * 任务工厂类
 * @author MyPC
 *
 */
public class ActiveObjectFactory {

	public static ActiveObject createActiveObject() {
		Servant servant = new Servant();
		ActivationQueue queue = new ActivationQueue();
		ScheduleThread scheduler = new ScheduleThread(queue);
		Proxy proxy = new Proxy(scheduler,servant);
		scheduler.start();
		return proxy;
	}

}
