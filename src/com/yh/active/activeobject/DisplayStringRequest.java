package com.yh.active.activeobject;

/**
 * ��ʾ����������
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
