package org.great.chatchat.client;

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
			
			System.out.println("�������ǳƣ�");
			bw.write(rs.readLine());
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
	@Override
	public void run() {
		while(!isRun){
			try {
				System.out.println("�������������ݣ�");
				bw.write(rs.readLine());
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
}
