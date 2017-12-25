package org.great.privatechat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1",10086);
			
			SendThread st = new SendThread(s);
			st.start();
			ReceiveThread ct = new ReceiveThread(s);
			ct.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
