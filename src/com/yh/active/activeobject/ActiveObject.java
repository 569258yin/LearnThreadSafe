package com.yh.active.activeobject;

public interface ActiveObject {

	/**
	 * ÖÆ×÷×Ö·û´®
	 * @param i
	 * @param fillchar
	 * @return
	 */
	public abstract Result makeString(int i, char fillchar);

	/**
	 * ÏÔÊ¾×Ö·û´®
	 * @param string
	 */
	public abstract void displayString(String string);

}
