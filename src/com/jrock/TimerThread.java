package com.jrock;

public class TimerThread implements Runnable{

	public void run() {
		new Timer().displayTime();
	}

}
