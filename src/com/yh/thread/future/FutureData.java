package com.yh.thread.future;

public class FutureData implements TData{
	private RealData realData = null;
	private boolean ready = false;
	
	public synchronized void setRealData(RealData realData) {
		if(ready){
			return;
		}
		this.realData = realData;
		this.ready = true;
		notifyAll();
	}

	@Override
	public synchronized String getContent() {
		while(!ready){
			try {
				wait();
			} catch (Exception e) {
			}
		}
		return realData.getContent();
	}

}
