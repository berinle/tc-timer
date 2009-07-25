package com.jrock;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HelloWorld {
	private List hellos = new ArrayList();

	private int counter;
	
	   public void sayHello() {
	      synchronized(hellos) {
	         hellos.add("Hello, World " + new Date());
	         for(Iterator it = hellos.iterator();it.hasNext();) {
	            System.out.println(it.next());
	         }
	      }
	   }
	   
	   public void countUp(){
		   counter++;
		   System.out.println(" counter is now: " + counter);
		   if(counter >= 100) {
			   System.out.println("***** resetting counter ******");
			   counter = 0;
		   }
	   }

	   public static void main(String[] args) throws Exception {
	      //new HelloWorld().sayHello();
		   
		   while(true){
			   new HelloWorld().countUp();
			   Thread.sleep(100);			  
		   }
	   }

}
