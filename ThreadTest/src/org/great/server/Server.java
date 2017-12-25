package org.great.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(10086);
			
			ServerSocketThread sst = new ServerSocketThread(ss);
			sst.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
