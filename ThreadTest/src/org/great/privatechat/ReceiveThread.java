package org.great.privatechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {

	private Socket s = null;
	private BufferedReader br;
	private boolean isRunning = false;
	public ReceiveThread(Socket s) {
		this.s = s;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.isRunning = true;
			try {
				this.s.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		while(!this.isRunning){
			if(null != this.br){
				String msg;
				try {
					msg = br.readLine();
					System.out.println("msg: " + msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					this.isRunning = true;
					try {
						this.s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
