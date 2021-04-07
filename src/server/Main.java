package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;	

public class Main {
	
	public static void main(String[] args) throws IOException {
		ServerSocket sock = new ServerSocket(6666);
		ConnectionHandler con = new ConnectionHandler(sock);
		ArrayList<Socket> cons;
		con.start();
		System.out.println("server started, waiting for connections");
		DataInputStream dis;
		Scanner sc = new Scanner(System.in);
		String str;
		try {
			PrintWriter out = null;
			for(;;){
//				System.out.println("20");
//				if(sc.hasNextLine()) {
//					System.out.println("22");
//					switch(sc.nextLine()){
//					case "exit":
//						out.close();
//						sock.close();
//						System.exit(0);
//						break;
//					case "clients":
//						for(int i = 0; i < con.getConnections().size(); i++){
//							System.out.println(i + ") "); //add nicks here
//						}
//						break;
//					default:
//						System.out.println("asdad");
//						break;
//					}
//				}
				str="";
				System.out.print(""); //don't delete
				cons = (ArrayList<Socket>) con.getConnections().clone();
				for(Socket s : cons) {
					dis = new DataInputStream(s.getInputStream());
					while(dis.available() > 0) {
						System.out.println(""); //don't delete
						str = dis.readUTF().toString();
						System.out.println("LOG: message=" + str);
						for(Socket so : con.getConnections()) {
							if(!so.equals(s)) {
								out = new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
								System.out.println(str);
								out.write(str + "\n");
								System.out.println("LOG: sent message to " + so.hashCode());
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
