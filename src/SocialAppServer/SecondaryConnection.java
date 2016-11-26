package SocialAppServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by kemo on 06/11/2016.
 */
class SecondaryConnection extends Thread {
    private int port;
    private static ArrayList<NotificationSimplexConnection> connections;
    SecondaryConnection(int port)
    {
        this.port = port;
        connections = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
        for (int i = port; i < port + 10; i++) {
            try {
                //noinspection InfiniteLoopStatement
                while (true) {
                    ServerSocket serverSocket = new ServerSocket(i);
                    Socket client = serverSocket.accept();
                    connections.add(new NotificationSimplexConnection(client));
                }
            } catch (IOException e) {
              //just ignore
            }
        }
    }
}
