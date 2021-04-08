package server;

import java.util.ArrayList;
import java.io.*;	
import java.net.*;

public class ConnectionHandler extends Thread {
	private ArrayList<Socket> connections = new ArrayList<Socket>();
	ServerSocket sock;
	
	public ConnectionHandler(ServerSocket sock){
		this.sock=sock;
	}
	
	public void run() {
		for(;;) {
			try {
				this.connections.add(sock.accept());
				System.out.println("CON: new connection ("+connections.size()+")");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Socket> getConnections() {
		return connections;
	}
}
