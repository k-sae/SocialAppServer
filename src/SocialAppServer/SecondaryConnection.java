package SocialAppServer;

import SocialAppGeneral.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

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
                ServerSocket serverSocket = new ServerSocket(i);
                System.out.println("Notification Socket: " + i);
                //noinspection InfiniteLoopStatement
                while (true) {

                    Socket client = serverSocket.accept();
                    connections.add(new NotificationSimplexConnection(client));
                }
            }
            catch (IOException e) {
              e.printStackTrace();
            }
        }
    }
    public static void sendNotification(String id, Command cmd)
    {
        //IDK i have just wrote a normal foreach statement then intellij modified it :D
        //i will try it if it worked i'll leave it
        connections.stream().filter(connection -> Objects.equals(connection.id, id)).forEach(connection -> connection.sendCommand(cmd));
    }
}
