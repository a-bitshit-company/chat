package server;

import java.util.ArrayList;
import java.io.*;	
import java.net.*;

public class ConnectionHandler extends Thread {
	private ArrayList<ClientInfo> connections = new ArrayList<ClientInfo>();
	ServerSocket sock;

	public ConnectionHandler(ServerSocket sock){
		this.sock=sock;
	}
	
	public void run() {
		for(;;) {
			try {
				ClientInfo ci = new ClientInfo(sock.accept());
				this.connections.add(ci);
				System.out.println("CON: new connection ("+connections.size()+")");
				DataInputStream dis = new DataInputStream(ci.getSock().getInputStream());
				if(dis.available() > 0){
					ci.setName(dis.readUTF().toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void remove(ArrayList<ClientInfo> del){
		for (ClientInfo d : del) {
			System.out.println(d.getName() + " disconnected");
			connections.remove(d);
		}
	}
	public ArrayList<ClientInfo> getConnections() {
		return connections;
	}
}
