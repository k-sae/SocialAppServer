package SocialAppServer;

import SocialAppGeneral.Group;

import java.io.IOException;
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
        final int secondaryPort = 6101;
        SecondaryConnection secondaryConnection = new SecondaryConnection(secondaryPort);
        secondaryConnection.start();
        //start main Connection up here
        startMainConnection(mainPort);

    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void startMainConnection(int startPort) {
        for (int i = startPort; i < startPort + 10; i++) {
            try {
                //create a server socket where the client will connect on the specified startPort
                ServerSocket serverSocket = new ServerSocket(i);
                System.out.println("Server Socket is: " + serverSocket.getLocalPort());
                while (true) { // loop where the server wait for client to start his connection may need to make these process in another thread
                    Socket client = serverSocket.accept();
                    HalfDuplexConnection clientConnection = new HalfDuplexConnection(client);
                    clientConnections.add(clientConnection);
                }
            } catch (IOException e) {
                //Error reporting 4 Debugging later will use log class
            } catch (Exception e) {
                //TODO
                //Export to log files
            }
        }

    }
}
