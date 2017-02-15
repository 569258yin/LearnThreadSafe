package com.yh.specificstorage;

public class Main {

	public static void main(String[] args) {
		System.out.println("main BEGIN");
		new ClinetThread("Alice").start();
		new ClinetThread("Bobby").start();
		new ClinetThread("Chris").start();
		System.out.println("END");
	}

}
