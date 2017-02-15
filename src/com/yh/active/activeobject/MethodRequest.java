package com.yh.active.activeobject;
/**
 * 抽象类，用于定义执行任务的公共类型
 * 传递实际执行操作的对象，和未来结果存放对象
 * @author MyPC
 *
 */
public abstract class MethodRequest {
	protected final Servant servant;
	protected final FutureResult future;
	
	protected MethodRequest(Servant servant,FutureResult future) {
		this.servant = servant;
		this.future = future;
	}
	
	public abstract void execute();

}
