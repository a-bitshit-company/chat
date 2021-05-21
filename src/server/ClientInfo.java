package server;

import java.net.Socket;

public class ClientInfo {
	private Socket sock;
	private String name;
	
	public ClientInfo(Socket con, String name) {
		this.sock = con;
		this.name = name;
	}
	public ClientInfo(Socket con) {
		this.sock = con;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Socket getSock() {
		return sock;
	}

	public String getName() {
		return name;
	}
	

	

}
