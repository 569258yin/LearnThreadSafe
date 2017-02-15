package com.yh.active.activeobject;
/**
 * �����࣬���ڶ���ִ������Ĺ�������
 * ����ʵ��ִ�в����Ķ��󣬺�δ�������Ŷ���
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
