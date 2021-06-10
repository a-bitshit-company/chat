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
		InputHandler con;
	    Scanner in = new Scanner(System.in);
	    System.out.print("Nickname: ");
	    String name = in.nextLine();
		try {
			s = new Socket("localhost", 6666);
			con = new InputHandler(new BufferedReader(new InputStreamReader(s.getInputStream())));
			con.start();
			
			dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF(name);
			for(;;) {
				while(in.hasNext()) {
					dout.writeUTF(in.nextLine());
					dout.flush();
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