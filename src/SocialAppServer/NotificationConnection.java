package SocialAppServer;

import java.net.Socket;

/**
 * Created by kemo on 06/11/2016.
 */
public class NotificationConnection extends ClientConnection {
    public NotificationConnection(Socket clientSocket) {
        super(clientSocket);
        //Read Client data im another thread so it doesnt disturb the server
    }

    @Override
    public void startConnection() {
        //TODO #kareem
    }
}
