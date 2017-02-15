package com.yh.active.activeobject;

/**
 * 立即返回结果的实例，当真实结果得到后会保存到此对象中
 * @author MyPC
 *
 */
public class FutureResult extends Result{
	private Result result;
	private boolean ready = false;
	public synchronized void setResult(Result result) {
		this.result = result;
		this.ready = true;
		notifyAll();
	}

	@Override
	public synchronized Object getResultValue() {
		while(!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result.getResultValue();
	}

}
