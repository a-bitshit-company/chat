package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TimerTask;

public class PingTask extends TimerTask {
    ConnectionHandler con;

    PingTask(ConnectionHandler con){
        this.con = con;
    }

    @Override
    public void run() {
        ArrayList<ClientInfo> inactive = new ArrayList<ClientInfo>();
        try {
            for(ClientInfo inf : con.getConnections()) {
                PrintWriter out = null;
                out = new PrintWriter(new OutputStreamWriter(inf.getSock().getOutputStream()));
                out.println("\\ping");
                if (out.checkError()) {
                    inactive.add(inf);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        con.remove(inactive);
    }
}
