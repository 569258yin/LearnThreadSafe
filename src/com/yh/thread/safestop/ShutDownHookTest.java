package com.yh.thread.safestop;

public class ShutDownHookTest {

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(
				new Thread(){
					@Override
					public void run() {
						System.out.println("***********");
						System.out.println(Thread.currentThread().getName() +"SHUTDOWN HOOK!");
						System.out.println("***************");
						super.run();
					}
				});
		System.out.println("main: sleep");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		
		System.exit(0);
		
		System.out.println("Main1 EDN");
	}

}
