package server;

import java.io.*;
import java.net.*;	

public class Main {
	
	public static void main(String[] args) throws IOException {
		ServerSocket sock = new ServerSocket(6666);
		ConnectionHandler con = new ConnectionHandler(sock);
		con.start();
		try {
			PrintWriter out = null;
			System.out.println("LOG: server started");
			for(;;){
				String str="";
				System.out.println("LOG: reached server.Main:17");
				for(Socket s : con.connections) {
					System.out.println("LOG: reached server.Main:19");
					DataInputStream dis = new DataInputStream(s.getInputStream());
					System.out.println("LOG: reached server.Main:21");
					str = dis.readUTF().toString(); // TODO: this shit blocks
				}
				if(!str.equals("")){
					System.out.println("LOG: message=" + str);
					for(Socket s : con.connections) {
						out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
						out.write(str + "\n");
						out.flush();
					}
				}
			}
			//out.close();
			//sock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
