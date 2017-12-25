package org.great.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现多客户端
 * @author damon
 *
 */
public class ServerSocketThread extends Thread {

	private ServerSocket ss;
	private List<TaskHandle> sockets;
	private boolean isRun = false;
	public ServerSocketThread(ServerSocket ss) {
		this.ss = ss;
		this.sockets = new ArrayList<TaskHandle>();
	}
	@Override
	public void run() {
		while(!this.isRun){
			Socket s = null;
			try {
				s = this.ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				this.isRun = true;
				try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				s = null;
			}
			if(null != s){
				TaskHandle th = new TaskHandle(s,this);
				th.start();
				this.sockets.add(th);//管理客户端请求链接Socket
			}
		}
		
	}
	public ServerSocket getSs() {
		return ss;
	}
	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}
	public List<TaskHandle> getSockets() {
		return sockets;
	}
	public void setSockets(List<TaskHandle> sockets) {
		this.sockets = sockets;
	}
}
