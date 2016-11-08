package SocialAppServer;

import SocialAppGeneral.Command;
import SocialAppGeneral.Connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 23/10/2016.
 */
//
abstract class ClientConnection implements Connection {
    protected Socket clientSocket;

    public ClientConnection( Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    //TODO #kareem
    //Check for user input info
    @Override
   public abstract void startConnection();

    public void sendCommand(Command command) {
        //TODO #kareem
        //Try to handle it in another thread (EPIC FAIL) use another way
        // try to make it in another thread thats is w8ing for notify in loop
        try {
            //TODO #kareem
            //add the new interface where all classes implements its
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            //dataOutputStream.writeUTF(command);
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
            System.out.println("E: send Data\t" + e.getMessage());
        }

    }
}



