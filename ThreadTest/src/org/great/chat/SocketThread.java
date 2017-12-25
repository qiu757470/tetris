package org.great.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * 实现多客户端请求链接线程
 */
public class SocketThread extends Thread {

	private ServerSocket ss;
	//List<Socket> sockets;
	//将所有的Sokcet放入集合统一管理
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
				s = ss.accept();//客户端请求链接
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
			
			//创建一个消息接收线程
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
