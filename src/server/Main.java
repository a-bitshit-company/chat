package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Main {
	
	

	public static void main(String[] args) {
		
	ArrayList<Socket> sessions = new ArrayList<Socket>();
		
		try {
			PrintWriter out = null;
			ServerSocket sock = new ServerSocket(6666);
			System.out.println("Echo-Server wartet...");
			
			for(;;){
				Socket s = sock.accept();
				sessions.add(s);
				
				System.out.println(sessions.size());
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = (String) dis.readUTF();
				System.out.println("message= " + str);

				out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
				out.write("> " + str + "\n");
				out.flush();
			}
			//out.close();
			//sock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
