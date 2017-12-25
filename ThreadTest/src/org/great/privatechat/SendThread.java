package org.great.privatechat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread {

	private Socket s = null;
	private Scanner sc = null;
	private PrintStream ps;
	private boolean isRunning = false;
	public SendThread(Socket s) {
		this.s = s;
		sc = new Scanner(System.in);
		try {
			ps = new PrintStream(s.getOutputStream(), true);
			
			//发送客户端身份标记
			ps.println("client2");
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
			if(null != ps){
				ps.println(this.sc.nextLine());
			}
		}
	}
}
