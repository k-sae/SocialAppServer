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
    Socket clientSocket;

    ClientConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        sendVerificationCode();
        startConnection();
    }
    //TODO #kareem
    //Check for user input info
    @Override
   public abstract void startConnection();

    public void sendCommand(Command command) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeUTF(command.toString());
        } catch (
                IOException e)
        {
            //For debugging
            onUserDisconnection();
            System.out.println("User Disconnected");
        } catch (
                Exception e)
        {
            //TODO
            //Export to Log
            System.out.println("E: send Data\t" + e.getMessage());
        }

    }
    private void sendVerificationCode()
    {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.write(VERIFICATION.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void onUserDisconnection()
    {

    }
}



