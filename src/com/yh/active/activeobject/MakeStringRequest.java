package com.yh.active.activeobject;

/**
 * 生成字符串任务请求
 * @author MyPC
 *
 */
public class MakeStringRequest extends MethodRequest{
	private final int count;
	private final char fillchar;
	public MakeStringRequest(Servant servant, FutureResult future, int count, char fillchar) {
		super(servant, future);
		this.count = count;
		this.fillchar = fillchar;
	}
	
	@Override
	public void execute() {
		Result result = servant.makeString(count,fillchar);
		System.out.println("Real Result Over...");
		future.setResult(result);
	}

}
