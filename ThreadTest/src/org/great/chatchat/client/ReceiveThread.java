package org.great.chatchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {

	private Socket s;
	private BufferedReader rs;
	private boolean isRun = false;
	public ReceiveThread(Socket s) {
		this.s = s;
		try {
			rs = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.isRun = true;
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
		while(!this.isRun){
			try {
				System.out.println(rs.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				this.isRun = true;
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
