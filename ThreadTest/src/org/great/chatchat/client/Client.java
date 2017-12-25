package org.great.chatchat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 10086);
			
			SendThread st = new SendThread(s);
			st.start();
			ReceiveThread rt = new ReceiveThread(s);
			rt.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
