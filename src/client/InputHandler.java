package client;

import java.io.BufferedReader;

public class InputHandler extends Thread {
	BufferedReader reader;
	
	public InputHandler(BufferedReader reader) {
		this.reader=reader;
	}
	
	public void run() {
		String response;
		for(;;) {
			try {
//				System.out.println("test");
//				while((response = reader.readLine()) != null){
//					System.out.println("> " + response);
//				}
				while(reader.ready()) {
					System.out.println("> " + reader.readLine());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
