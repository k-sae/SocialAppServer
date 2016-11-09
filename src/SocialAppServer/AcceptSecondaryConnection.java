package SocialAppServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kemo on 06/11/2016.
 */
public class AcceptSecondaryConnection extends Thread {
   private int port;
    public AcceptSecondaryConnection(int port)
    {
        this.port = port;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket client = serverSocket.accept();
                NotificationConnection notificationConnection = new NotificationConnection(client);
            }
        } catch (IOException e) {
            //Ignore dc User
            //TODO #kareem
            //remove from online list
        }
    }
}
