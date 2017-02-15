package com.yh.active;

import com.yh.active.activeobject.ActiveObject;
import com.yh.active.activeobject.ActiveObjectFactory;

public class Main {

	public static void main(String[] args) {
		ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
		new MakerClientThread("Alice",activeObject).start();
		new MakerClientThread("Bobby",activeObject).start();
		new DisplayClientThread("Chris",activeObject).start();
		
	}

}
