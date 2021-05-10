package com.macower.businessdata.task;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MailTask {


	public void test(){
		
	}
	static int i = 1 ;
	public static void main(String[] args) {
		
		 Timer timer=new Timer();
		 Date d=new Date();
		 TimerTask task=new TimerTask(){

			@Override
			public void run() {
				i++ ; 
				System.out.println(i);
				System.out.println(new Date()+"===========test================"+this.scheduledExecutionTime());
			    try {
			    	while(true){
			    		Thread.sleep(3000) ;
			    	}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
		 };
		 timer.schedule(task, d,1000); 
		//timer.scheduleAtFixedRate(task, 0,1000); 
		 
		 //timer.cancel() ;
	}

}
