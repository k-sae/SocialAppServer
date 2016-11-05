package SocialAppServer;

import SocialAppGeneral.Command;
import SocialAppGeneral.Connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kemo on 23/10/2016.
 */
//
public class ClientConnection implements Connection {
    private  ServerSocket serverSocket;
   protected Socket clientSocket;

    public ClientConnection(ServerSocket serverSocket, Socket clientSocket) {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        //Read Client data im another thread so it doesnt disturb the server
        ReceiveClientCommand readClientData = new ReceiveClientCommand(clientSocket, this);
        readClientData.start();
    }

    @Override
    public void startConnection() {
        //TODO #kareem
        //Check for user input info
    }

    @Override
    public void sendCommand(Command command) {
        //TODO #kareem
        //Try to handle it in another thread (EPIC FAIL) use another way
        // try to make it in another thread thats is w8ing for notify in loop
        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.writeObject(command);
            //if there is error remove this
//                    objectOutputStream.close();
        } catch (
                IOException e)

        {
            //For debugging
            System.out.println("send Data\t" + e.getMessage());
        } catch (
                Exception e)
        {
            //TODO
            //Export to Log
            System.out.println("send Data\t" + e.getMessage());
        }

    }
}



