package org.great.chat;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(10086);
			
			SocketThread st = new SocketThread(ss);
			st.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
