package com.yh.active.activeobject;

public interface ActiveObject {

	/**
	 * �����ַ���
	 * @param i
	 * @param fillchar
	 * @return
	 */
	public abstract Result makeString(int i, char fillchar);

	/**
	 * ��ʾ�ַ���
	 * @param string
	 */
	public abstract void displayString(String string);

}
