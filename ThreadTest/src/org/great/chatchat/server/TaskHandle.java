package org.great.chatchat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TaskHandle extends Thread{

	private Socket s;
	private ServerSocketThread serverSocketThread;
	private BufferedReader rs;
	private PrintStream ps;
	private String flag;
	private boolean isRun = false;
	public TaskHandle(Socket s, ServerSocketThread serverSocketThread) {
		this.s= s;
		this.serverSocketThread = serverSocketThread;
		try {
			rs = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			this.flag = rs.readLine();
			
			ps = new PrintStream(this.s.getOutputStream());
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
			String msg = null;
			try {
				msg = rs.readLine();
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
			//Ë½ÁÄ£ºprivate:client2,content=ÄãºÃ
			//ÈºÁÄ£ºall:content=ÄãºÃ
			if(null != msg){
				//String task = msg.substring(0, msg.indexOf(":"));m
				if(msg.contains("private")){//Ë½ÁÄ
					String to = msg.substring(msg.indexOf(":") + 1, msg.indexOf(","));
					System.out.println("to: " + to);
					String content = msg.substring(msg.indexOf("=") + 1);
					System.out.println("content: " + content);
					for(TaskHandle srt : this.serverSocketThread.getSockets()){
						if(srt.getFlag().equals(to)){
							srt.senddMsg(msg);
						}else{
							continue;
						}
					}
				}else if(msg.contains("all")){//ÈºÁÄ
					String content = msg.substring(msg.indexOf("=") + 1);
					System.out.println("content: " + content);
					for(TaskHandle srt : this.serverSocketThread.getSockets()){
						if(srt == this){
							continue;
						}
						srt.senddMsg(msg);
					}
				}
			}
		}
	}
	private void senddMsg(String msg) {
		ps.println(msg);
		ps.flush();
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
