package SocialAppServer;

import java.net.Socket;

/**
 * Created by kemo on 06/11/2016.
 */
public class HalfDuplexConnection extends ClientConnection {
    public HalfDuplexConnection(Socket clientSocket) {
        super(clientSocket);
        //Read Client data im another thread so it doesnt disturb the server
        ReceiveClientCommand readClientData = new ReceiveClientCommand(clientSocket, this);
        readClientData.start();
    }

    @Override
    public void startConnection() {
        //TODO #kareem
        //check for use info if false end the connection
    }
}
