package org.great.server;

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
//	private String flag;
	private String e_no = "1";
	private String e_pwd = "123456";
	private boolean isRun = false;
	public TaskHandle(Socket s, ServerSocketThread serverSocketThread) {
		this.s= s;
		this.serverSocketThread = serverSocketThread;
		try {
			rs = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			//this.flag = rs.readLine();
			
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
			//˽�ģ�private:client2,content=���
			//Ⱥ�ģ�all:content=���
			if(null != msg){
				//String task = msg.substring(0, msg.indexOf(":"));m
				if(msg.contains("private")){//˽��
					String to = msg.substring(msg.indexOf(":") + 1, msg.indexOf(","));
					System.out.println("to: " + to);
					String content = msg.substring(msg.indexOf("=") + 1);
					System.out.println("content: " + content);
//					for(TaskHandle srt : this.serverSocketThread.getSockets()){
//						if(srt.getFlag().equals(to)){
//							srt.senddMsg(msg);
//						}else{
//							continue;
//						}
//					}
				}else if(msg.contains("all")){//Ⱥ��
					String content = msg.substring(msg.indexOf("=") + 1);
					System.out.println("content: " + content);
					for(TaskHandle srt : this.serverSocketThread.getSockets()){
						if(srt == this){
							continue;
						}
						srt.senddMsg(msg);
					}
				}else if(msg.contains("Login")){//�����¼
					//Login:e_no=1,e_pwd=123456
					String e_no = msg.substring(msg.indexOf("=") + 1, msg.indexOf(","));
					String e_pwd = msg.substring(msg.lastIndexOf("=") + 1);
					if(e_no.equals(this.e_no) && e_pwd.equals(this.e_pwd)){
						senddMsg("Login:success!-e_no=1,e_pwd=123456");
					}
				}
			}
		}
	}
	private void senddMsg(String msg) {
		ps.println(msg);
		ps.flush();
	}
//	public String getFlag() {
//		return flag;
//	}
//	public void setFlag(String flag) {
//		this.flag = flag;
//	}
	
}
