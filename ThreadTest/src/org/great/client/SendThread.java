package org.great.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SendThread extends Thread {

	private Socket s;
	private BufferedReader rs;
	private boolean isRun = false;
	private BufferedWriter bw;
	public SendThread(Socket s) {
		this.s = s;
		rs = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
//			System.out.println("«Î ‰»ÎÍ«≥∆£∫");
//			bw.write(rs.readLine());
//			bw.newLine();
//			bw.flush();
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
		while(!isRun){
			try {
				System.out.println("«Î ‰»Î¡ƒÃÏƒ⁄»›£∫");
				String msg = rs.readLine();
				String e_no = msg.substring(msg.indexOf("=") + 1, msg.indexOf(","));
				String e_pwd = msg.substring(msg.lastIndexOf("=") + 1);
				
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
	
	public void sendMsg(String msg){
		try {
			bw.write(msg);
			bw.newLine();
			bw.flush();
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
