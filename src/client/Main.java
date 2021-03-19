package client;

import java.io.BufferedReader;	
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		Socket s = null;
		DataOutputStream dout = null;
		BufferedReader in;
		String response= "";
		try {
			s = new Socket("localhost", 6666);
			dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF("Client connected");
			for(;;) {
				if(sc.hasNext()) {
					System.out.print("used< ");
					dout.writeUTF(sc.nextLine());
					dout.flush();
				}
				
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				response = "";
				while((response = in.readLine()) != null) {
					System.out.println("das> " + response);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				sc.close();
				s.close();
				dout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}