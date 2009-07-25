package com.jrock;

public class TimerThread implements Runnable{

	public void run() {
		try {
			while(true){
				new Timer().displayTime();
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
