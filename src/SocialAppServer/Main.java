package SocialAppServer;

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
    public static void main(String[] args){
        clientConnections = new ArrayList<>();
       int port = args.length < 2 ? 6060 : Integer.parseInt(args[0]);
       try{
           //create a server socket where the client will connect on the specified port
           ServerSocket serverSocket = new ServerSocket(port);
           while(true){ // loop where the server wait for client to start his connection in a separate thread
               Socket client = serverSocket.accept();
               ClientConnection clientConnection = new ClientConnection(serverSocket,client);
               clientConnections.add(clientConnection);
           }
       }catch (IOException e) {
           //Error reporting 4 Debugging later will use log class
           System.out.println("main\t" + e.getMessage() );
       }
       catch (Exception e)
       {
           //TODO
           //Export to log files
       }
    }
}
