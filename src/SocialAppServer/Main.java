package SocialAppServer;


import SocialAppServer.Connections.ClientConnection;
import SocialAppServer.Connections.HalfDuplexConnection;
import SocialAppServer.Connections.SecondaryConnection;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kemo on 23/10/2016.
 */
public class Main {
    //List that holds all connections
    //TODO #kareem
    //dont forget to remove clientConnection when he dcs
    public static List<ClientConnection> clientConnections;
    public static void main(String[] args) {
        clientConnections = new ArrayList<>();
        final int mainPort = 6000;
        final int secondaryPort = 6100;
        SecondaryConnection secondaryConnection = new SecondaryConnection(secondaryPort);
        secondaryConnection.start();
        //start main Connection up here
        startMainConnection(mainPort);

    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void startMainConnection(int startPort) {
        for (int i = startPort; i < startPort + 10; i++)
            try {
                //create a server socket where the client will connect on the specified startPort
                ServerSocket serverSocket = new ServerSocket(i);
                System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());
                System.out.println("Server Socket is: " + serverSocket.getLocalPort());
                while (true) { // loop where the server wait for client to start his connection may need to make these process in another thread
                    Socket client = serverSocket.accept();
                    System.out.println("Client Connected");
                    HalfDuplexConnection clientConnection = new HalfDuplexConnection(client);
                    clientConnections.add(clientConnection);
                }
            } catch (Exception ignored) {
            }

    }
}
