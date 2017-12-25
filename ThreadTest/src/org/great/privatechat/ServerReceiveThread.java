package org.great.privatechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*
 * ������Ϣ�߳�
 */
public class ServerReceiveThread extends Thread {

	private Socket s;
	private BufferedReader br;
	private PrintStream ps;
	private SocketThread st;
	private boolean isRunning = false;
	//private String clientName;
	public ServerReceiveThread(Socket s,SocketThread st) {
		this.s = s;
		this.st = st;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			//���ղ�������
			//this.clientName = br.readLine();
			this.setName(br.readLine());
			
			ps = new PrintStream(s.getOutputStream(), true);
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
			try {
				String msg = br.readLine();
				//client2,content=���!
				String to = msg.substring(0, msg.indexOf(","));//[0,100)
				System.out.println("to: " + to);
				String content = msg.substring(msg.indexOf("=") + 1);
				System.out.println("content: " + content);
				//˽��
				for(ServerReceiveThread srt : this.st.getSockets()){
					if(srt.getName().equals(to)){
						srt.senddMsg(msg);
					}else{
						continue;
					}
				}
//				//���������ͻ������ӵ�Socket����������Ϣ
//				for(ServerReceiveThread srt : this.st.getSockets()){
//					if(srt == this){
//						continue;
//					}
//					srt.senddMsg(msg);
//				}
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
	
	//������Ϣ����
	public void senddMsg(String msg){
		ps.println(msg);
	}
}
