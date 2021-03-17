package client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.CharBuffer;

public class Main {
	public static void main(String[] args) {
		Socket s = null;
		DataOutputStream dout = null;
		try {
			s = new Socket("localhost", 6666);
			dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF("Hello Server");
			dout.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while (true) {
			}

		} catch (Exception e) {
			System.out.println("hier");
			e.printStackTrace();
		} finally {
			try {
				s.close();
				dout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static String getinput() {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
		    CharBuffer buffer = CharBuffer.allocate(1024);
		    while (reader.read(buffer) != -1) {
		        buffer.flip();
		        sb.append(buffer);
		        buffer.clear();
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb);
		return sb.toString();
	}
}
