package com.yh.active.activeobject;

/**
 * 显示任务工作请求
 * @author YH
 *
 */
public class DisplayStringRequest extends MethodRequest {
	private String string;
	protected DisplayStringRequest(Servant servant, String string) {
		super(servant, null);
		this.string = string;
	}

	@Override
	public void execute() {
		servant.displayString(string);
	}

}
