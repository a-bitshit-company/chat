package server;

import java.io.*;	
import java.net.*;
import java.util.ArrayList;

public class Main {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		ServerSocket sock = new ServerSocket(6666);
		ConnectionHandler con = new ConnectionHandler(sock);
		ArrayList<ClientInfo> cons;
		con.start();
		System.out.println("server started, waiting for connections");
		DataInputStream dis;
		String str;
		try {
			PrintWriter out = null;
			for(;;){
				str="";
				System.out.print(""); //don't delete
				cons = (ArrayList<ClientInfo>) con.getConnections().clone();
				for(ClientInfo inf : cons) {
					//System.out.println(inf.getSock());
					dis = new DataInputStream(inf.getSock().getInputStream());
					while(dis.available() > 0) {
						System.out.println(""); //don't delete
						str = dis.readUTF().toString();
						System.out.println("LOG: message=" + str);
						for(ClientInfo info : cons) {
							if(!info.getSock().equals(inf.getSock())) {
								out = new PrintWriter(new OutputStreamWriter(info.getSock().getOutputStream()));
								System.out.println(str);
								out.write(str + "\n");
								System.out.println("LOG: sent message to " + info.getSock().hashCode());
								out.flush();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
