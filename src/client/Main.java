package client;

import java.io.BufferedReader;	
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Socket s = null;
		DataOutputStream dout = null;
		ConnectionHandler con;
	    Scanner in = new Scanner(System.in);
		try {
			s = new Socket("localhost", 6666);
			con = new ConnectionHandler(new BufferedReader(new InputStreamReader(s.getInputStream())));
			con.run();
			
			dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF("Client connected");
			for(;;) {
				System.out.print("< ");
				while(in.hasNext()) {
					dout.writeUTF(in.nextLine());
					dout.flush();
					System.out.print("< ");
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				s.close();
				dout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}