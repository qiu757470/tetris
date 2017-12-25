package org.great.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * ʵ�ֶ�ͻ������������߳�
 */
public class SocketThread extends Thread {

	private ServerSocket ss;
	//List<Socket> sockets;
	//�����е�Sokcet���뼯��ͳһ����
	List<ServerReceiveThread> sockets;
	private boolean isRunning = false;
	public SocketThread(ServerSocket ss) {
		this.ss = ss;
		this.sockets = new ArrayList<ServerReceiveThread>();
	}
	@Override
	public void run() {
		while(!this.isRunning){
			Socket s = null;
			try {
				s = ss.accept();//�ͻ�����������
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				this.isRunning = true;
				try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//����һ����Ϣ�����߳�
			ServerReceiveThread srt = new ServerReceiveThread(s,this);
			srt.start();
			if(null != s){
				this.sockets.add(srt);
			}
		}
	}
	public List<ServerReceiveThread> getSockets() {
		return sockets;
	}
	public void setSockets(List<ServerReceiveThread> sockets) {
		this.sockets = sockets;
	}
}
