package com.yh.active.activeobject;
/**
 * 真实返回结果的实例
 * @author MyPC
 *
 */
public class RealResult extends Result{

	private final Object resultValue;
	
	public RealResult(Object resultValue) {
		this.resultValue = resultValue;
	}
	
	@Override
	public Object getResultValue() {
		return resultValue;
	}

}
