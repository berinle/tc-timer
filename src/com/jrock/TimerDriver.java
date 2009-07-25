package com.jrock;

public class TimerDriver {
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		System.out.println("from tc network: " + timer.getTimeRemaining());
		long timeRemaining = timer.getTimeRemaining();
		
		System.out.println("total count down time: " + timeRemaining + " (secs)");
		
		if(timeRemaining == 0){
			timer.reset();
			timeRemaining = timer.getTimeRemaining();
			//timer.setTimeRemaining(timeRemaining);
		}
		
		while(timeRemaining > 0){
			//System.out.println("Time remaining: " + timeRemaining--);
			
			int hr;
			int min;
			
			double hour = Math.floor(Math.max(timeRemaining/(60*60), 0));
			hr = new Double(hour).intValue();
			long secsLeft = timeRemaining % (60*60);
			double mins = Math.floor(Math.max(secsLeft/60, 0));
			min = new Double(mins).intValue();
			secsLeft = secsLeft % 60;
			long secs = secsLeft;
			
			System.out.println("Time remaining: " + hr + " hrs, " + min + " mins, " + secs + " secs");
			timer.setTimeRemaining(timeRemaining--); //write updated time back
			//timeRemaining--;
			
			if(timeRemaining == 0){
				timer.reset();
				timeRemaining = timer.getTimeRemaining();
			}
			
			try{
				Thread.sleep(1000);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
