package com.jrock;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

//git is cool!
public class Timer {
	private transient Vector<ScriptSession> dwrclients = new Vector<ScriptSession>();
	
	private long timeRemaining; //seconds
	private Date startTime;
	private Date endTime;
		
	private boolean init;
	//lock will keep track of active server in charge of decrementing clock
	private final Object lock = new Object(); 
	
	private transient boolean running;
	
	public Timer(){
		
		if(!init){
			Calendar c = Calendar.getInstance();		
			startTime = c.getTime();
			//c.add(Calendar.HOUR, 6);
			c.add(Calendar.MINUTE, 2);
			//c.add(Calendar.SECOND, 5);
			endTime = c.getTime();
			timeRemaining = (endTime.getTime() - startTime.getTime())/1000;
			init = true;
		}
	}
	
	public void setRunning(boolean running){
		this.running = running;
	}
		
	public void attach(){
		System.out.println( "attaching to server ");
		WebContext wctx = WebContextFactory.get();
		if(wctx == null) return;
		
		ScriptSession scriptSession = wctx.getScriptSession();
		if(scriptSession == null) return;
		
		dwrclients.add(scriptSession);
	}
	
	public long getTimeRemaining(){
		return timeRemaining;
	}
	
	public void setTimeRemaining(long timeRemaining){
		this.timeRemaining = timeRemaining;
	}
	

	public void reset() {
		System.out.println("resetting the timer...");
		init = false;
		Timer t = new Timer();
		this.timeRemaining = t.getTimeRemaining();
	}
	
	public void displayTime(){
		
		synchronized(lock){		
			
			System.out.println(" acquired lock, will be in charge of timer ");
			running = true;
			
			while(running){
				int hr;
				int min;
				
				double hour = Math.floor(Math.max(timeRemaining/(60*60), 0));
				hr = new Double(hour).intValue();
				long secsLeft = timeRemaining % (60*60);
				double mins = Math.floor(Math.max(secsLeft/60, 0));
				min = new Double(mins).intValue();
				secsLeft = secsLeft % 60;
				long secs = secsLeft;
				
				//System.out.println("Time remaining: " + hr + " hrs, " + min + " mins, " + secs + " secs");
				//format the time for display on front end
				StringBuilder sb = new StringBuilder();
				if(hr <= 9){
					sb.append("0").append(hr);
				} else{
					sb.append(hr);
				}
				sb.append(":");
				
				if(min <= 9){
					sb.append("0").append(min);
				} else{
					sb.append(min);
				}
				sb.append(":");
				
				if(secs <= 9){
					sb.append("0").append(secs);
				} else{
					sb.append(secs);
				}
				
				timeRemaining--; //write updated time back
				
				System.out.println("Time remaining: " + sb.toString());
				
				// Do a push with DWR
		//		WebContext wctx = WebContextFactory.get();
		//		if(wctx == null) return;
		//		
		//		ScriptSession scriptSession = wctx.getScriptSession();
		//		if(scriptSession == null) return;
				
				//TODO Push this to all clients!
				//Util page = new Util(scriptSession); 
				System.out.println("pushing info to (" + dwrclients.size() + ") clients");
				Util page = new Util(dwrclients);
				page.setValue("remainingTime", sb.toString());
				
				try{
					Thread.sleep(5000);
				} catch(InterruptedException e){
					System.out.println("==> " + e.getMessage());
				}
				//timeRemaining--;
				
				/*if(timeRemaining == 0){
					reset();
				}*/
			} //end while
		}
	}
	

}
