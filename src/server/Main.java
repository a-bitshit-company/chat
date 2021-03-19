package server;

import java.io.*;
import java.net.*;
import java.util.Scanner;	

public class Main {
	
	public static void main(String[] args) throws IOException {
		ServerSocket sock = new ServerSocket(6666);
		ConnectionHandler con = new ConnectionHandler(sock);
		con.start();
		System.out.println("server started, waiting for connections");
		DataInputStream dis;
		Scanner sc = new Scanner(System.in);
		String str;
		try {
			PrintWriter out = null;
			for(;;){
				if(sc.hasNextLine()) {
					switch(sc.nextLine()){
					case "exit":
						out.close();
						sock.close();
						System.exit(0);
						break;
					case "clients":
						for(int i = 0; i < con.getConnections().size(); i++){
							System.out.println(i + ") "); //add nicks here
							break;
						}
					}
				}
				str="";
				for(Socket s : con.getConnections()) {
					dis = new DataInputStream(s.getInputStream());
					if(dis.available() > 0) {
						str = dis.readUTF().toString();
					}
				}
				if(!str.equals("")){
					System.out.println("LOG: message=" + str);
					for(Socket s : con.getConnections()) {
						out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
						out.write(str + "\n");
						out.flush();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
