package org.great.threadtest;

public class PrintHandle {

	private boolean flag = false; 
	
	public synchronized void mainThread(){
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j = 1; j <= 20; j++){
			System.out.println(Thread.currentThread().getName() + ": " + j);
		}
		
		this.flag = false;
		this.notify();
	}
	
	public synchronized void sonThread(){
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j = 1; j <= 10; j++){
			System.out.println(Thread.currentThread().getName() + ": " + j);
		}
		
		this.flag = true;
		this.notify();
	}
}
