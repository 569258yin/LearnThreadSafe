package com.yh.thread.future;

public class RealData implements TData{
	private final String content; 
	
	public RealData(int count,char c) {
		System.out.println("    making RealData(" + count + "," + c +") BEGIN" );
		char[] buffer = new char[count];
		for (int i = 0; i < count; i++) {
			buffer[i] = c;
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
		System.out.println("    making RealData(" + count + "," + c +") END");
		this.content = new String(buffer);
	}
	
	@Override
	public String getContent() {
		return content;
	}

}
