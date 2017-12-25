package org.great.threadtest;

public class SonThread extends Thread {

	private PrintHandle handle;
	public SonThread(PrintHandle handle) {
		this.handle = handle;
	}

	@Override
	public void run() {
		for(int i = 0; i < 50; i++){
			this.handle.sonThread();
		}
	}
}
