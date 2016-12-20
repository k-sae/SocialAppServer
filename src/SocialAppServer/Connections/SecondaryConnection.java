package SocialAppServer.Connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kemo on 06/11/2016.
 */
public class SecondaryConnection extends Thread {
    private int port;

    public SecondaryConnection(int port)
    {
        this.port = port;
    }

    @Override
    public void run() {
        super.run();
        for (int i = port; i < port + 10; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(i);
                System.out.println("Notification Socket: " + i);
                //noinspection InfiniteLoopStatement
                while (true) {

                    Socket client = serverSocket.accept();
                   new NotificationSimplexConnection(client);
                }
            }
            catch (IOException e) {
              e.printStackTrace();
            }
        }
    }

}
