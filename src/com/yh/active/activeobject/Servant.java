package com.yh.active.activeobject;

/**
 * 实际操作实例，最根本的工作
 * @author MyPC
 *
 */
public class Servant implements ActiveObject{

	public Result makeString(int count, char fillchar) {
		char[] buffer = new char[count];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = fillchar;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return new RealResult(new String(buffer));
	}

	public void displayString(String string) {
		try {
			System.out.println("displayString: "+string);
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
